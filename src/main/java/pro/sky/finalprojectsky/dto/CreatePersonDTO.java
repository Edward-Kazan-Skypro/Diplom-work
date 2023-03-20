package pro.sky.finalprojectsky.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreatePersonDTO {
    @Email(regexp = ".+@.+[.]..+")
    private String email;
    @NotBlank
    private String userName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String password;
    @Pattern(regexp = "\\+7\\d{10}")
    private String phone;

}
