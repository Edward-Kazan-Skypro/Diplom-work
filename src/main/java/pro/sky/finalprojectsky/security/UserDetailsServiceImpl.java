package pro.sky.finalprojectsky.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.finalprojectsky.repository.UserRepository;


@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserDetailsPasswordService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        pro.sky.finalprojectsky.entity.User user = getUserByUsername(username);

        return new MyUserDetails(user);
    }

    @Override

    public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
        pro.sky.finalprojectsky.entity.User user = getUserByUsername(userDetails.getUsername());

        user.setPassword(newPassword);

        MyUserDetails updatedUserDetails = new MyUserDetails(userRepository.save(user));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(updatedUserDetails, null, updatedUserDetails.getAuthorities())
        );

        return updatedUserDetails;
    }

    private pro.sky.finalprojectsky.entity.User getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Пользователь с email: \"%s\" не найден", username)));
    }
}