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
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.dto.UserDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class UserController {

    //1u new
    @PostMapping(value = "/users/set_password",
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(summary = "setPassword",
            description = "setPassword",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = NewPasswordDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto body) {
        return null;
    }

    //2u new
    @GetMapping(value = "/users/me",
            produces = {"application/json"})
    @Operation(summary = "Получить информацию об авторизованном пользователе",
            description = "getUser",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<UserDto> getUser() {
        return null;
    }


    //3u new
    @PatchMapping(value = "/users/me",
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(summary = "Обновить информацию об авторизованном пользователе",
            description = "updateUser",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})

    ResponseEntity<UserDto> updateUser(@RequestBody UserDto body) {
        return null;
    }

    //4u new
    @PatchMapping(value = "/users/me/image",
            consumes = {"multipart/form-data"})
    @Operation(summary = "Обновить аватар авторизованного пользователя",
            description = "updateUserImage",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<Void> updateUserImage(@Parameter(description = "file detail") @RequestPart("file") MultipartFile image) {
        return null;
    }
}
