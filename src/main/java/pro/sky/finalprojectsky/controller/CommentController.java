package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import pro.sky.finalprojectsky.dto.ResponseWrapper;
import pro.sky.finalprojectsky.service.AdsService;
import pro.sky.finalprojectsky.service.CommentService;

import javax.servlet.http.HttpServletResponse;


@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class CommentController {
    Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final AdsService adsService;
    private final CommentService commentsService;

    @GetMapping(value = "/ads/{adsId}/comments",
            produces = { "application/json" })
    @Operation(summary = "Получить комментарии объявления",
            description = "getComments",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseWrapper<AdsCommentDto> getAdsComments(@PathVariable("adsId") Integer adsId) {
        logger.info("Request for get ad comment");
        return ResponseWrapper.of(commentsService.getAdsComments(adsId));
    }

    @PostMapping(value = "/ads/{adsId}/comments",
            produces = { "application/json" },
            consumes = { "application/json" })
    @Operation(summary = "Добавить комментарий к объявлению",
            description = "addAdsComment",
            tags={ "Комментарии" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdsCommentDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public AdsCommentDto addAdsComment(@PathVariable("adsId") Integer adsId,
                                       @RequestBody AdsCommentDto adsCommentDto) {
        return commentsService.addAdsComment(adsId, adsCommentDto);
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
    public ResponseEntity<HttpStatus> deleteAdsComment(@PathVariable("adId") Integer adId,
                                                    @PathVariable("commentId") Integer commentId,
                                                    Authentication authentication  ) {
        if (commentsService.deleteAdsComment(adId, commentId, authentication)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
    }
    @Operation(summary = "Поиск комментария по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsCommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @GetMapping("/{adKey}/comments/{id}")
    public AdsCommentDto getAdsComment(@PathVariable Integer adKey, @PathVariable Integer id) {
        logger.info("Request for get ad comment");
        return commentsService.getAdsComment(adKey, id);
    }

    @Operation(summary = "Изменение комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененный комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsCommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @PatchMapping("/{adKey}/comments/{id}")
    public ResponseEntity<AdsCommentDto> updateAdsComment(@PathVariable Integer adKey, @PathVariable Integer id,
                                                          @RequestBody AdsCommentDto updateAdsCommentDto,
                                                          Authentication authentication) {
        logger.info("Request for update ad comment");
        AdsCommentDto updatedAdsCommentDto = commentsService.updateAdsComment(adKey, id,
                updateAdsCommentDto, authentication);
        if (updateAdsCommentDto.equals(updatedAdsCommentDto)) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(updatedAdsCommentDto);
    }
}