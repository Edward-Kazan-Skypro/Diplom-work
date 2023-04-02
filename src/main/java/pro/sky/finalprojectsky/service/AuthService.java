package pro.sky.finalprojectsky.service;

import org.springframework.security.core.userdetails.User;

/**
 * Интерфейс сервиса для регистрации пользователя и входа
 */
public interface AuthService {

    /**
     * @param username Логин (email)
     * @param password Пароль
     */

    void login(String username, String password);

    /**
     * @param user Объект пользователя
     */
    void register(User user);

    void register(pro.sky.finalprojectsky.entity.User user);
}