package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.service.ImageService;
import pro.sky.finalprojectsky.service.UserService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    //1u new
    @PostMapping(value = "/users/set_password",
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(summary = "setPassword",
            description = "setPassword",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NewPasswordDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto newPasswordDto) {
        userService.newPassword(newPasswordDto.getNewPassword(), newPasswordDto.getCurrentPassword());
        return ResponseEntity.ok(newPasswordDto);
    }

    //2u new
    @GetMapping(value = "/users/me",
            produces = {"application/json"})
    @Operation(summary = "Получить информацию об авторизованном пользователе",
            description = "getUser",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<UserDto> getUserMe(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserMe(authentication));
    }

    //3u new
    @PatchMapping(value = "/users/me",
            produces = {"application/json"},
            consumes = {"application/json"})
    @Operation(summary = "Обновить информацию об авторизованном пользователе",
            description = "updateUser",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))),

            @ApiResponse(responseCode = "204", description = "No Content"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found")})

    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    //4u new
    @SneakyThrows
    @PatchMapping(value = "/users/me/image",
            consumes = {"multipart/form-data"})
    @Operation(summary = "Обновить аватарку пользователя",
            description = "updateUserImage",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "404", description = "Not Found")})
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image,
                                         Authentication authentication) {
        if (imageService.updateUserImage(image, authentication)){
            return ResponseEntity.ok().body("Аватарка пользователя обновлена");
        } else {
            return ResponseEntity.ok().body("Аватарка пользователя НЕ обновлена");
        }
    }
}
