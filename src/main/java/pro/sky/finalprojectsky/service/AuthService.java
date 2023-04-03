package pro.sky.finalprojectsky.service;

import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.entity.User;

@Component
public interface AuthService {
    void login(String username, String password);

    void register(User user);
}