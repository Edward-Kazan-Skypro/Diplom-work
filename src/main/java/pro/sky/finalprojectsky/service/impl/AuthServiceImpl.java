package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.AuthService;

import javax.validation.ValidationException;

/**
 * Реализация сервиса для регистрации пользователя и входа
 */
//@Transactional
@RequiredArgsConstructor
@Service

public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    @Override
    public void login(String username, String password) {
        logger.info("Was invoked method for user authorization");
        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            logger.warn("the password is incorrect");
            throw new BadCredentialsException("Неверно указан пароль!");
        }
    }

    @Override
    public void register(User user) {
        logger.info("Was invoked method for user registration");
        if (userRepository.existsByEmail(user.getEmail())) {
            logger.warn("user already exists");
            throw new ValidationException(String.format("Пользователь \"%s\" уже зарегистрирован!", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("user registered");
        userRepository.save(user);
    }
}