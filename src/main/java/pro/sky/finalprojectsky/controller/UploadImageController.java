package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.service.ImageService;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
public class UploadImageController {

    private final ImageService imageService;

    public UploadImageController(ImageService imageService) {
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
            description = "получение сохраненной картинки (массива байт)",
            tags={ "Изображения" })
    public byte[] getImage(@PathVariable String id) {
        return imageService.getImageBytesArray(Integer.parseInt(id));
    }
}
