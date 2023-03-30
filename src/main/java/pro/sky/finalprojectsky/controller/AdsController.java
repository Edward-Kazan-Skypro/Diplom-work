package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.*;
import pro.sky.finalprojectsky.mappers.CommentMapper;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.service.impl.AdsServiceImpl;
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AdsController {

    private final AdsServiceImpl adsServiceImpl;

    public AdsController(AdsServiceImpl adsServiceImpl) {
        this.adsServiceImpl = adsServiceImpl;
    }

    //--------------------------------------------------------------
    //----------- NEW CODE -----------------------------------------
    //--------------------------------------------------------------

    //Ads block

    //1a new
    @GetMapping(value = "/ads",
            produces = { "application/json" })
    @Operation(summary = "",
            description = "Получить все объявления",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperAds.class))) })

    ResponseEntity<ResponseWrapperAds> getALLAds(){
        return null;
    }

    //2a new
    @PostMapping(value = "/ads",
            produces = { "application/json" },
            consumes = { "multipart/form-data" })
    @Operation(summary = "addImageToAds",
            description = "Добавить объявление",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<AdsDto> addAd(@RequestParam(value="properties", required=false)  CreateAdsDto properties,
                                 @RequestPart("image") MultipartFile image){
        return null;
    }

    //3a new
    @GetMapping(value = "/ads/{id}",
            produces = { "application/json" })
    @Operation(summary = "Получить информацию об объявлении",
            description = "getFullAd",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = FullAds.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id){
        return null;
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
    ResponseEntity<String> removeAds(@PathVariable("id") Integer id){
        if (adsServiceImpl.removeAds(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Объявление удалено");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявление с таким id отсутствует");
        }
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

    ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id, @RequestBody CreateAdsDto body){
        return null;
    }


    //6a new
    @GetMapping(value = "/ads/me",
            produces = { "application/json" })
    @Operation(summary = "Получить объявления авторизованного пользователя",
            description = "getAdsMe",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseWrapperAds.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<ResponseWrapperAds> getAdsMe(){
        return null;
    }

    //7a new
    @PatchMapping(value = "/ads/{id}/image",
            produces = { "application/octet-stream" },
            consumes = { "multipart/form-data" })
    @Operation(summary = "Обновить картинку объявления",
            description = "updateImage",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/octet-stream", array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<List<byte[]>> updateImage(@PathVariable("id") Integer id,
                                             @RequestPart("image") MultipartFile image){
        return null;
    }

    //Comment block

    //1c new
    @GetMapping(value = "/ads/{adsId}/comments",
            produces = { "application/json" })
    @Operation(summary = "Получить комментарии объявления",
            description = "getComments",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("adsId") Integer adsId) {
        return  null;
    }


    //2c new
    @PostMapping(value = "/ads/{adsId}/comments",
            produces = { "application/json" },
            consumes = { "application/json" })
    @Operation(summary = "Добавить комментарий к объявлению",
               description = "addComment",
               tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<String> addComment(@PathVariable("adsId") Integer adsId,
                                           @RequestBody CommentDto commentDtoBody) {
        return null;
    }

    //3c new
    @GetMapping(value = "/ads/{adId}/comments/{commentId}",
            produces = { "application/json" })
    @Operation(summary = "Получить комментарий объявления",
            description = "getComments",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> getCommentById(@PathVariable("adId") Integer adId,
                                              @PathVariable("commentId") Integer commentId) {
        return null;
    }

    //4c new
    @DeleteMapping(value = "/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Удалить комментарий",
            description = "deleteSelectedComment",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<Void> deleteSelectedComment(@PathVariable("adId") Integer adId,
                                               @PathVariable("commentId") Integer commentId) {
        return null;
    }

    //5c new
    @PatchMapping(value = "/ads/{adId}/comments/{commentId}",
            produces = { "application/json" },
            consumes = { "application/json" })
    @Operation(summary = "Обновить комментарий",
            description = "updateAdsComment",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Comment.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> updateAdsComment(@PathVariable("adId") Integer adId,
                                                     @PathVariable("commentId") Integer commentId,
                                                     @RequestBody Comment body){
            return ResponseEntity.status(HttpStatus.OK).
                    body(adsServiceImpl.
                            updateAdsComment(adId, commentId, body));
    }
}

