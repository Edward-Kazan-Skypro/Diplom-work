package pro.sky.finalprojectsky.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.finalprojectsky.dto.LoginReqDto;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.Role;
import pro.sky.finalprojectsky.service.AuthService;

import static pro.sky.finalprojectsky.dto.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
//@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login",
            consumes = { "application/json" })
    @Operation(summary = "Авторизация пользователя",
            description = "",
            tags={ "Авторизация" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto) {
        if (authService.login(loginReqDto.getUserName(), loginReqDto.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping(value = "/register",
            consumes = { "application/json" })
    @Operation(summary = "Регистрация пользователя",
            description = "",
            tags={ "Регистрация" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),

            @ApiResponse(responseCode = "401", description = "Unauthorized"),

            @ApiResponse(responseCode = "403", description = "Forbidden"),

            @ApiResponse(responseCode = "404", description = "Not Found") })
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
        Role role = registerReqDto.getRole() == null ? USER : registerReqDto.getRole();
        if (authService.register(registerReqDto, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
