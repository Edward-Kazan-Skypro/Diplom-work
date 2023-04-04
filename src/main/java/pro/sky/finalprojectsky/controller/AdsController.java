package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.*;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.service.AdsService;
import pro.sky.finalprojectsky.service.ImageService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AdsController {

    private final AdsService adsService;

    private final ImageService imageService;

    public AdsController(AdsService adsService, ImageService imageService) {
        this.adsService = adsService;
        this.imageService = imageService;
    }

    //1a new
    @GetMapping(value = "/ads",
            produces = { "application/json" })
    @Operation(summary = "",
            description = "Получить все объявления",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto[].class))) })

    public ResponseWrapper<AdsDto> getALLAds(){
        return ResponseWrapper.of(adsService.getAllAds());
    }

    //2a new
    @PostMapping(value = "/ads",
            produces = { "application/json" },
            consumes = { "multipart/form-data" })
    @SneakyThrows
    @ExceptionHandler
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(summary = "addAd",
            description = "Добавить объявление",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    public ResponseEntity<AdsDto> addAd(@RequestPart("image") MultipartFile image,
                                 @RequestPart("properties") @Valid CreateAdsDto dto){
        return ResponseEntity.ok(adsService.createAds(dto, image));
    }

    //3a new
    @GetMapping(value = "/ads/{id}",
            produces = { "application/json" })
    @Operation(summary = "Получить информацию об объявлении",
            description = "getFullAd",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Ads.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public FullAdsDto getAds(@PathVariable("id") Integer id){
        return adsService.getFullAdsDto(id);
    }

    //4a new
    @DeleteMapping(value = "/ads/{id}")
    @Operation(summary = "removeAds",
            description = "удалить выбранное объявление",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden") })
    public ResponseEntity<HttpStatus> removeAds(@PathVariable Integer id, Authentication authentication) throws IOException {
        if (adsService.removeAds(id, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }

    //5a new
    @PatchMapping(value = "/ads/{id}",
            produces = { "application/json" },
            consumes = { "application/json" })
    @Operation(summary = "Обновить информацию об объявлении",
            description = "updateAds",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    public ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id,
                                     Authentication authentication,
                                     @RequestBody AdsDto updatedAdsDto){
        AdsDto updateAdsDto = adsService.updateAds(id, updatedAdsDto, authentication);
        if (updateAdsDto.equals(updatedAdsDto)) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(updateAdsDto);
    }


    //6a new
    @GetMapping(value = "/ads/me",
            produces = { "application/json" })
    @Operation(summary = "Получить объявления авторизованного пользователя",
            description = "getAdsMe",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AdsDto[].class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseWrapper<AdsDto> getAdsMe(){
        return ResponseWrapper.of(adsService.getAdsMe());
    }

    //7a new
    @SneakyThrows
    @PatchMapping(value = "/ads/{id}/image",
            produces = { "application/octet-stream" },
            consumes = { "multipart/form-data" })
    @Operation(summary = "Обновить картинку объявления",
            description = "updateImage",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/octet-stream",
                            array = @ArraySchema(schema = @Schema(implementation = AdsDto.class)))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseEntity<AdsDto> updateImage(@PathVariable("id") Integer id,
                                             @RequestPart("image") MultipartFile image,
                                             Authentication authentication){
        return ResponseEntity.ok(imageService.updateImage(image, authentication, id));
    }
}

