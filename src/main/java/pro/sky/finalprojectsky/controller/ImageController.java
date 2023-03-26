package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

//@Slf4j
//@CrossOrigin(value = "http://localhost:3000")
//@RestController
public class ImageController {

    @PatchMapping(value = "/image/{id}",
            produces = { "application/octet-stream" },
            consumes = { "multipart/form-data" })
    @Operation(summary = "updateAdsImage",
            description = "загрузка изображения к объявлению",
            tags={ "Изображения" })

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),

            @ApiResponse(responseCode = "404",
                    description = "Not Found") })
    ResponseEntity<List<byte[]>> updateImage(@PathVariable("id") Integer id, @RequestPart("file") MultipartFile image){
        return null;
    }
}
