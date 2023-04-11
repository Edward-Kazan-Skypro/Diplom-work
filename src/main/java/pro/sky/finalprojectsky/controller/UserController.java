package pro.sky.finalprojectsky.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.UserMapper;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.UserService;
import pro.sky.finalprojectsky.service.ImageService;
import pro.sky.finalprojectsky.dto.*;
import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "UserController")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final ImageService imageService;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Operation(summary = "Создание пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Созданный пользователь",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            },
            tags = "Users"

    )
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody CreateUserDto createUserDto) {
        logger.info("Request for add user");
        return ResponseEntity.ok(userService.createUser(createUserDto));
    }

    @Operation(summary = "Просмотр всех пользователей",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователи",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            },
            tags = "Users"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserMe(Authentication authentication) {
        logger.info("Request for get users");
        return ResponseEntity.ok(userService.getUserMe(authentication));
    }

    @Operation(summary = "Изменение информации о пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененная информация",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            },
            tags = "Users"
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Request for update user");
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @Operation(summary = "Изменение пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новый пароль",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPasswordDto.class)
                            )
                    )
            },
            tags = "Users"
    )
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@Valid @RequestBody NewPasswordDto newPasswordDto) {
        logger.info("Request for create new password");
        userService.newPassword(newPasswordDto.getNewPassword(), newPasswordDto.getCurrentPassword());
        return ResponseEntity.ok(newPasswordDto);
    }

    @Operation(summary = "Поиск пользователя по id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный пользователь",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            },
            tags = "Users"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        logger.info("Request for get user by id");
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Изменение роли пользователя User/Admin",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Измененные данные",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Role.class)
                            )
                    )
            },
            tags = "Users"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}/updateRole")
    public ResponseEntity<UserDto> updateRole(@PathVariable("id") long id, Role role) {
        logger.info("Request for update user role");
        return ResponseEntity.ok(userService.updateRole(id, role));
    }

    @Operation(summary = "Просмотр изображения пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Изображение, найденное по id",
                            content = @Content(
                                    mediaType = MediaType.IMAGE_PNG_VALUE,
                                    schema = @Schema(implementation = Image.class)
                            )
                    )
            },
            tags = "Image"
    )@GetMapping(value = "images/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        logger.info("Request for get image by id");
        return ResponseEntity.ok(imageService.getImageBytesArray(id));
    }

    @SneakyThrows
    @Operation(summary = "Загрузка новой аватарки пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Новое изображение",
                            content = @Content(
                                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    )
            },
            tags = "Image"
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserDto> updateUserImage(Authentication authentication, @Parameter(in = ParameterIn.DEFAULT, description = "Загрузите сюда новое изображение",
            schema = @Schema())
    @RequestPart(value = "image") @Valid MultipartFile image) {
        logger.info("Request for update user image");
        Image newImage = imageService.uploadUserImage(image, authentication);
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        user.setImage(newImage);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}