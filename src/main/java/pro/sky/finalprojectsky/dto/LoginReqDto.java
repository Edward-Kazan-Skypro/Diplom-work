package pro.sky.finalprojectsky.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static pro.sky.finalprojectsky.constant.Registr.EMAIL_REGISTR;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginReqDto {

    @Email(regexp = EMAIL_REGISTR)
    @Schema(example = "user@user.ru")
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;
}