package pro.sky.finalprojectsky.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class PersonDTO {
    private Long id;
    @Email(regexp = ".+@.+[.]..+")//regulars
    private String email;
    @NotBlank//not blank
    private String userName;
    @NotBlank//not blank
    private String secondName;
    @NotBlank
    @Pattern(regexp = "\\+7\\d{10}")//regulars
    private String phone;

}
