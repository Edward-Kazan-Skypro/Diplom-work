package pro.sky.finalprojectsky.dto;

import lombok.*;


@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {
    private String password;
    private String username;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
