package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.dto.ResponseWrapper;
import pro.sky.finalprojectsky.service.AdsService;
import pro.sky.finalprojectsky.service.CommentService;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(value = "http://localhost:3000")
//@RequiredArgsConstructor
@RestController
public class CommentController {
    private final AdsService adsService;
    private final CommentService commentsService;

    public CommentController(AdsService adsService, CommentService commentService) {
        this.adsService = adsService;
        this.commentsService = commentService;
    }
    @GetMapping(value = "/ads/{adsId}/comments",
            produces = { "application/json" })
    @Operation(summary = "Получить комментарии объявления",
            description = "getComments",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseWrapper<CommentDto> getComments(@PathVariable("adsId") Integer adsId) {
        return ResponseWrapper.of(commentsService.getComments(adsId));
    }

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
    public CommentDto addComment(@PathVariable("adsId") Integer adsId,
                                      @RequestBody CommentDto commentDto) {
        return commentsService.addComment(adsId, commentDto);
    }

    @DeleteMapping(value = "/ads/{adId}/comments/{commentId}")
    @Operation(summary = "Удалить комментарий",
            description = "deleteSelectedComment",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("adId") Integer adId,
                                                    @PathVariable("commentId") Integer commentId,
                                                    Authentication authentication  ) {
        if (commentsService.deleteComment(adId, commentId, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }

    @PatchMapping(value = "/ads/{adId}/comments/{commentId}",
            produces = { "application/json" },
            consumes = { "application/json" })
    @Operation(summary = "Обновить комментарий",
            description = "updateAdsComment",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> updateComment(@PathVariable("adId") Integer adId,
                                                @PathVariable("commentId") Integer commentId,
                                                @RequestBody CommentDto updateCommentDto,
                                                Authentication authentication){
        CommentDto updatedCommentDto = commentsService.updateComment(adId, commentId,
                updateCommentDto, authentication);
        if (updateCommentDto.equals(updatedCommentDto)) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(updatedCommentDto);
    }
}