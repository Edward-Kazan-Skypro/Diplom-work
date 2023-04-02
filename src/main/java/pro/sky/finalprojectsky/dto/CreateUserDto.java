package pro.sky.finalprojectsky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static pro.sky.finalprojectsky.constant.Registr.EMAIL_REGISTR;
import static pro.sky.finalprojectsky.constant.Registr.PHONE_REGISTR;

@Data
public class CreateUserDto {


    @Email(regexp = EMAIL_REGISTR)
    @Schema(example = "user@user.ru")
    private String email;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Pattern(regexp = PHONE_REGISTR)
    private String phone;

}