package pro.sky.finalprojectsky.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.Role;

@Service
public class AuthService {

    private final UserDetailsManager manager;

    private final PasswordEncoder encoder;

    public AuthService(UserDetailsManager manager) {
        this.manager = manager;
        this.encoder = new BCryptPasswordEncoder();
    }


    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        String encryptedPasswordWithoutEncryptionType = encryptedPassword.substring(8);
        return encoder.matches(password, encryptedPasswordWithoutEncryptionType);
    }


    public boolean register(RegisterReqDto registerReqDto, Role role) {
        if (manager.userExists(registerReqDto.getUserName())) {
            return false;
        }
        manager.createUser(
                User.withDefaultPasswordEncoder()
                        .password(registerReqDto.getPassword())
                        .username(registerReqDto.getUserName())
                        .roles(role.name())
                        .build()
        );
        return true;
    }
}
