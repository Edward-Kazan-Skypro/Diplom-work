package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.dto.ResponseWrapper;
import pro.sky.finalprojectsky.service.AdsService;
import pro.sky.finalprojectsky.service.CommentsService;

import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/ads")
@Tag(name = "Комментарии", description = "CommentController")

public class CommentController {

    Logger logger = (Logger) LoggerFactory.getLogger(AdsController.class);


    private final AdsService adsService;
    private final CommentsService commentsService;


    @Operation(summary = "Просмотр комментариев к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарии",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto[].class)
                            )
                    )
            },
            tags = "Comments"
    )
    @GetMapping("/{adKey}/comments")
    public ResponseWrapper<CommentDto> getComments(@PathVariable int adKey) {
        logger.info("Request for get ad comment");
        return AddDefaultCharsetFilter.ResponseWrapper.of(commentsService.getComments(adKey));
    }

    @Operation(summary = "Написать комментарий к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @PostMapping("/{adKey}/comments")
    public CommentDto addComments(@PathVariable long adKey, @RequestBody CommentDto commentDto) {
        logger.info("Request for add ad comment");
        return commentsService.addComment(adKey, commentDto);
    }

    @Operation(summary = "Удаление комментариев",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удаленный комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @DeleteMapping("/{adKey}/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable int adKey, @PathVariable long id,
                                                    Authentication authentication) {
        logger.info("Request for delete ad comment");
        if (commentsService.deleteComment(adKey, id, authentication)) {
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
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @GetMapping("/{adKey}/comments/{id}")
    public CommentDto getComment(@PathVariable int adKey, @PathVariable long id) {
        logger.info("Request for get ad comment");
        return commentsService.getComment(adKey, id);
    }

    @Operation(summary = "Изменение комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененный комментарий",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = CommentDto.class)
                            )
                    )
            },
            tags = "Comments"
    )
    @PatchMapping("/{adKey}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable int adKey, @PathVariable long id,
                                                    @RequestBody CommentDto updateCommentDto,
                                                    Authentication authentication) {
        logger.info("Request for update ad comment");
        CommentDto updatedCommentDto = commentsService.updateComment(adKey, id,
                updateCommentDto, authentication);
        if (updateCommentDto.equals(updatedCommentDto)) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(updatedCommentDto);
    }
}