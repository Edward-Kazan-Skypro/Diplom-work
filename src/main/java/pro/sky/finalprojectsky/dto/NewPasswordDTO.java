package pro.sky.finalprojectsky.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewPasswordDTO {
    @NotBlank
    private String currentPassword;
    @NotBlank
    private String newPassword;
}
