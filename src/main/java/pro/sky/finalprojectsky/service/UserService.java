package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import pro.sky.finalprojectsky.dto.CreateUserDto;
import pro.sky.finalprojectsky.dto.Role;
import pro.sky.finalprojectsky.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserDto createUserDto);

    List<UserDto> getUsers();

    UserDto getUserMe(Authentication authentication);

    UserDto updateUser(UserDto user);

    UserDto getUserById(Integer id);

    void newPassword(String newPassword, String currentPassword);

    UserDto updateRole(Integer id, Role role);
}