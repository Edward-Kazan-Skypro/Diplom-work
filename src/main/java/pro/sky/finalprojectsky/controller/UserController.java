package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
    ResponseEntity<String> setPassword(@RequestBody NewPasswordDto body) {
        if (userService.setNewPassword(body)){
            return ResponseEntity.status(HttpStatus.OK).body("Пароль успешно изменен");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Смена пароля не удалась...");
        }
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
        //Здесь не знаю как получить юзера - это гет запрос, на входе ничего нет, как искать юзера в репозитории?
        //Понятно, что пользователь, наверно, уже авторизован, но как получить эти данные?
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

    ResponseEntity<String> updateUser(@RequestBody UserDto body) {
        if (userService.updateUser(body)){
            return ResponseEntity.status(HttpStatus.OK).body("Информация о пользователе обновлена");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден");
        }
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
