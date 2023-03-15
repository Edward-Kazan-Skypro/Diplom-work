package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.service.AdsService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    // /ads/{ad_pk}/comments/{id}
    @Operation(summary = "updateComments", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/{ad_pk}/comments/{id}",
            produces = {"*/*"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<Comment> updateComments(@Parameter(in = ParameterIn.PATH,
            required = true, schema = @Schema())
            @PathVariable("ad_pk") String adPk,
            @Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
            @PathVariable("id") Integer id,
            @Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema())
            @Valid @RequestBody Comment body) {
        return null;
    }


    // /ads/{id}
    @Operation(summary = "получить объявление по id", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/{id}",
            produces = {"*/*"},
            method = RequestMethod.GET)
    ResponseEntity<String> getAds(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
                                   @PathVariable("id") Long id) {
        FullAds ads = adsService.getAdsById(id);
        if (ads != null){
            return ResponseEntity.status(HttpStatus.FOUND).body(ads.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявление не найдено");
        }
    }
}
