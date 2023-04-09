package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.AuthService;
import javax.validation.ValidationException;


@RequiredArgsConstructor
@Service

public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    @Override
    public void login(String username, String password) {
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Неверно указан пароль!");
        }
    }

    @Override
    public void register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException(String.format("Пользователь \"%s\" уже зарегистрирован!", user.getEmail()));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}