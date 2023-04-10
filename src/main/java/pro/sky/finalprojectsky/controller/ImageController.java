package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.service.ImageService;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upl")
    @Operation(summary = "saveImage",
            description = "сохранение картинки, возвращается путь к сохраненной картинке",
            tags={ "Изображения" })
    public String saveImage (@RequestParam MultipartFile image) throws IOException {
        return imageService.saveImage(image).getFilePath();
    }

    @GetMapping(value = "/images/{id}/", produces = {MediaType.IMAGE_PNG_VALUE})
    @Operation(summary = "getImage",
            description = "получение сохраненной картинки",
            tags={ "Изображения" })
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Image image = imageService.getImageById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getId() + "\"")
                .body(image.getByteData());
    }
}
