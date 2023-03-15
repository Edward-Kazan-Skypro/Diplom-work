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
import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:8080")
@RestController
public class AdsController {

    private final AdsService adsService;

    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    @Operation(summary = "updateComments", description = "", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/{ads_id}/comments/{comments_id}",
            produces = {"*/*"},
            consumes = {"application/json"},
            method = RequestMethod.PATCH)
    ResponseEntity<Comment> updateComments(@PathVariable("ads_id") Long adsId,
                                           @PathVariable("comments_id") Long commentId,
                                           @RequestBody Comment comment) {
        return null;
    }

    @Operation(summary = "removeAds", description = "", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")})
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity<String> removeAds(@PathVariable("id") Long id) {
        if (adsService.deleteAdsById(id)){
            return ResponseEntity.status(HttpStatus.FOUND).body("Ads deleted");
            produces = {"*/*"},
            method = RequestMethod.GET)
    ResponseEntity<String> getAds(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
                                  @PathVariable("id") Long id) {
        FullAds ads = adsService.getAdsById(id);
        if (ads != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ads.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ads NOT deleted");
        }
    }


}

    // /ads/me
    @Operation(summary = "получить объявления пользователя по идентификатору", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/{userId}",
            produces = {"*/*"},
            method = RequestMethod.GET)
    ResponseEntity<String> getAdsMe(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
                                    @PathVariable("userId") Long userId) {
        List<FullAds> adsMe = adsService.getAdsMe(userId);
        if (adsMe != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(adsMe.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявления не найдены");
        }
    }

    // /ads/
    @Operation(summary = "добавить объявление", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @RequestMapping(value = "/ads/",
            produces = {"*/*"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<FullAds> addAds(@Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema())
                                   @Valid @RequestBody FullAds ads) {
        FullAds newAds = adsService.addAds(ads);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAds);
    }

    // /ads/
    @Operation(summary = "получить все объявления", tags = {"Объявления"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = FullAds.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @RequestMapping(value = "/ads/",
            produces = {"*/*"},
            method = RequestMethod.GET)
    ResponseEntity<String> getAllAds() {
        List<FullAds> allAds = adsService.getAllAds();
        if (allAds != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(allAds.toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявления не найдены");
        }
    }
}