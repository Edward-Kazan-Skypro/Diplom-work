package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Image;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserDto {
    private Long id;
    @Email(regexp = ".+@.+[.]..+")//regulars
    private String emailAsUserName;
    @NotBlank//not blank
    private String firstName;
    @NotBlank//not blank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "\\+7\\d{10}")//regulars
    private String phone;
    private String avatarURL;
}
