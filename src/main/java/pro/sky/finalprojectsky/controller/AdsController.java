package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.*;
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


//
//    @Operation(summary = "updateComments", description = "обновление комментария", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = Comment.class))),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "403", description = "Forbidden"),
//            @ApiResponse(responseCode = "404", description = "Not Found")})
//    @RequestMapping(value = "/ads/{ads_id}/comments/{comments_id}",
//            produces = {"*/*"},
//            consumes = {"application/json"},
//            method = RequestMethod.PATCH)
//    ResponseEntity<String> updateComments(@PathVariable("ads_id") Integer adsId,
//                                          @PathVariable("comments_id") Integer commentId,
//                                          @RequestBody Comment comment) {
//        if (adsService.updateCommentInAds(adsId, commentId, comment)) {
//            return ResponseEntity.status(HttpStatus.FOUND).body("Comment - " + commentId + " in Ads is updated");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment - " + commentId + " in Ads is NOT updated");
//        }
//    }
//
//    @Operation(summary = "removeAds", description = "удаление объявления", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "No Content"),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "403", description = "Forbidden")})
//    @RequestMapping(value = "/ads/{id}",
//            method = RequestMethod.DELETE)
//    ResponseEntity<String> removeAds(@PathVariable("id") Integer id) {
//        if (adsService.deleteAdsById(id)) {
//            return ResponseEntity.status(HttpStatus.FOUND).body("Ads deleted");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ads NOT deleted");
//        }
//    }
//
//    @Operation(summary = "getAdsMe", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = FullAds.class))),
//            @ApiResponse(responseCode = "404", description = "Not Found")})
//    @RequestMapping(value = "/ads/{userId}",
//            produces = {"*/*"},
//            method = RequestMethod.GET)
//    ResponseEntity<String> getAdsMe(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
//                                    @PathVariable("userId") Integer userId) {
//        List<FullAds> adsMe = adsService.getAdsMe(userId);
//        if (adsMe != null) {
//            return ResponseEntity.status(HttpStatus.FOUND).body(adsMe.toString());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявления не найдены");
//        }
//    }
//
//    @Operation(summary = "addAds", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = FullAds.class))),
//            @ApiResponse(responseCode = "400", description = "Bad Request")})
//    @RequestMapping(value = "/ads/",
//            produces = {"*/*"},
//            consumes = {"application/json"},
//            method = RequestMethod.POST)
//    ResponseEntity<FullAds> addAds(@Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema())
//                                   @Valid @RequestBody FullAds ads) {
//        FullAds newAds = adsService.addAds(ads);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newAds);
//    }
//
//    @Operation(summary = "getAllAds", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = FullAds.class))),
//            @ApiResponse(responseCode = "404", description = "Not Found")})
//    @RequestMapping(value = "/ads/",
//            produces = {"*/*"},
//            method = RequestMethod.GET)
//    ResponseEntity<String> getAllAds() {
//        List<FullAds> allAds = adsService.getAllAds();
//        if (allAds != null) {
//            return ResponseEntity.status(HttpStatus.FOUND).body(allAds.toString());
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Объявления не найдены");
//        }
//    }
//
//    ///ads/{id} getFullAds
//
//    @Operation(summary = "getFullAds", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = FullAds.class))),
//            @ApiResponse(responseCode = "404", description = "Not Found")})
//    @RequestMapping(value = "/ads/{id}",
//            produces = {"*/*"},
//            method = RequestMethod.GET)
//    ResponseEntity<String> getAds(@Parameter(in = ParameterIn.PATH, required = true, schema = @Schema())
//                                  @PathVariable("id") Long id) {
//        return null;
//
//    }
//
//    // /ads/{id} updateAds (пустое)
//    @Operation(summary = "updateAds", tags = {"Объявления"})
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "*/*",
//                            schema = @Schema(implementation = FullAds.class))),
//            @ApiResponse(responseCode = "401", description = "Unauthorized"),
//            @ApiResponse(responseCode = "403", description = "Forbidden"),
//            @ApiResponse(responseCode = "404", description = "Not Found")})
//    @RequestMapping(value = "/ads/{id}", //@PatchMapping "/ads/{id}"
//            produces = {"*/*"},
//            consumes = {"application/json"},
//            method = RequestMethod.PATCH)
//    ResponseEntity<FullAds> updateAds(@Parameter(in = ParameterIn.PATH,
//            required = true, schema = @Schema())
//                                      @PathVariable("id") Integer id,
//                                      @Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema())
//                                      @Valid @RequestBody FullAds body) {
//        return null;
//    }
//
//

    //------------------------------------------------------------------------------------------
    //RawCode
    //------------------------------------------------------------------------------------------

    //1 ====
    @GetMapping(value = "/ads",
            produces = { "*/*" })
    @Operation(summary = "",
            description = "получить список всех объявлений",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))) })

    ResponseEntity<ResponseWrapperAds> getALLAds(){
        return null;
    }

    //2 ===
    @PostMapping(value = "/ads",
            produces = { "*/*" },
            consumes = { "multipart/form-data" })
    @Operation(summary = "addImageToAds",
            description = "добавить картинку к объявлению",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<AdsDto> addImageToAds(@RequestPart("file") MultipartFile image){
        return null;
    }
    //3 ===
    @GetMapping(value = "/ads/{ad_pk}/comments",
            produces = { "*/*" })
    @Operation(summary = "getComments",
            description = "получить все комментарии из указанного объявления",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperComment.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<ResponseWrapperComment> getComments(@PathVariable("ad_pk") String adPk) {
        return  null;
    }

    //4 ===
    @PostMapping(value = "/ads/{ad_pk}/comments",
            produces = { "*/*" },
            consumes = { "application/json" })
    @Operation(summary = "addComments",
               description = "добавление комментария к указанному объявлению",
               tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "*/*",
                            schema = @Schema(implementation = Comment.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> addComments(@PathVariable("ad_pk") String adPk,
                                           @RequestBody Comment body) {
        return null;
    }

    //5 ===
    @GetMapping(value = "/ads/{id}",
            produces = { "*/*" })
    @Operation(summary = "getFullAd",
            description = "получить объявление со всеми полями",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = FullAds.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<FullAds> getAds(@PathVariable("id") Integer id){
        return null;
    }

    //6 ===
    @DeleteMapping(value = "/ads/{id}")
    @Operation(summary = "removeAds",
            description = "удалить выбранное объявление",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden") })
    ResponseEntity<Void> removeAds(@PathVariable("id") Integer id){
        return null;
    }

    //7 ===
    @PatchMapping(value = "/ads/{id}",
            produces = { "*/*" },
            consumes = { "application/json" })
    @Operation(summary = "updateAds",
            description = "обновить выбранное объявление",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = AdsDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<AdsDto> updateAds(@PathVariable("id") Integer id, @RequestBody CreateAdsDTO body){
        return null;
    }

    //8 ===
    @GetMapping(value = "/ads/{ad_pk}/comments/{id}",
            produces = { "*/*" })
    @Operation(summary = "getComments",
            description = "получить выбранный комментарий из выбранного объявления",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = Comment.class))),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> getCommentById(@PathVariable("ad_pk") String adPk, @PathVariable("id") Integer id) {
        return null;
    }

    //9 ===
    @DeleteMapping(value = "/ads/{ad_pk}/comments/{id}")
    @Operation(summary = "deleteSelectedComment",
            description = "удалить выбранный комментарий из выбранного объявления",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<Void> deleteSelectedComment(@PathVariable("ad_pk") String adPk, @PathVariable("id") Integer id) {
        return null;
    }

    //10 ===
    @PatchMapping(value = "/ads/{ad_pk}/comments/{id}",
            produces = { "*/*" },
            consumes = { "application/json" })
    @Operation(summary = "updateComments",
            description = "обновить выбранный комментарий в выбранном объявлении",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = Comment.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<CommentDto> updateSelectedComment(@PathVariable("ad_pk") String adPk, @PathVariable("id") Integer id,  @RequestBody Comment body){
        return null;
    }

    //11 ===
    @GetMapping(value = "/ads/me",
            produces = { "*/*" })
    @Operation(summary = "getAdsMe",
            description = "получить список объявлений для пользователя",
            tags={ "Объявления" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = ResponseWrapperAds.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<ResponseWrapperAds> getAdsMe(@Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema())
                                                @Valid @RequestParam(value = "authenticated", required = false) Boolean authenticated, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "authorities[0].authority", required = false) String authorities0Authority, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "credentials", required = false) Object credentials, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "details", required = false) Object details, @Parameter(in = ParameterIn.QUERY, description = "" ,schema=@Schema()) @Valid @RequestParam(value = "principal", required = false) Object principal){
        return null;
    }
}

