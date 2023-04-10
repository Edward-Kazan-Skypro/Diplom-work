package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.User;

@Mapper
public interface UserMapper {
    default User convertRegisterReqDtoToEntity(RegisterReqDto dto) {
        return User
                .builder()
                .email(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .role(null)
                .build();
    }

    default UserDto convertEntityToUserDto(User user) {
        return UserDto
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .image(user.getImage().getFilePath())
                .build();
    }
}