package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.NewPasswordDTO;
import pro.sky.finalprojectsky.dto.PersonDTO;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class PersonController {

    @GetMapping(value = "/users/me",
            produces = { "*/*" })
    @Operation(summary = "getUser",
            description = "",
            tags={ "Пользователи" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = PersonDTO.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<PersonDTO> getUser() {
        return null;
    }


    @PostMapping(value = "/users/set_password",
            produces = { "*/*" },
            consumes = { "application/json" })
    @Operation(summary = "setPassword",
            description = "",
            tags={ "Пользователи" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = NewPasswordDTO.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    ResponseEntity<NewPasswordDTO> setPassword(@RequestBody NewPasswordDTO body) {
        return null;
    }


    @PatchMapping(value = "/users/me",
            produces = { "*/*" },
            consumes = { "application/json" })
    @Operation(summary = "updateUser",
            description = "",
            tags={ "Пользователи" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "*/*", schema = @Schema(implementation = PersonDTO.class))),

            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    //ResponseEntity<User> updateUser(@RequestBody User body)
    ResponseEntity<PersonDTO> updateUser(@RequestBody PersonDTO body)
    {
        return null;
    }


    @PatchMapping(value = "/users/me/image",
            consumes = { "multipart/form-data" })
    @Operation(summary = "updateUserImage",
            description = "UpdateUserImage",
            tags={ "Пользователи" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found") })

    ResponseEntity<Void> updateUserImage(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile image){
     return null;
    }

}
