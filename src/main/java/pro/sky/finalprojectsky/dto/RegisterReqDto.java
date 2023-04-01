package pro.sky.finalprojectsky.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterReqDto {
    @Email(regexp = ".+@.+[.]..+")//regulars
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @NotBlank
    @Pattern(regexp = "\\+7\\d{10}")//regulars
    private String phone;
    private Role role;
}
