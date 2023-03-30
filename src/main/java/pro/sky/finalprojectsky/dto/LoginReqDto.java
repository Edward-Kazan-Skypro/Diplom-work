package pro.sky.finalprojectsky.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
    private String password;
    private String userName;
}