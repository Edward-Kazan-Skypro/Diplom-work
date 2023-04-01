package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.dto.ResponseWrapperComment;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.service.AdsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class CommentController {

    private final AdsService adsService;

    public CommentController(AdsService adsService) {
        this.adsService = adsService;
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

    //4c new
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
                body(adsService.
                        updateAdsComment(adId, commentId, body));
    }

}
