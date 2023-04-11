package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pro.sky.finalprojectsky.dto.CreateUserDto;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.User;

/**
 * Interface of user mapper
 */
@Mapper
public interface UserMapper extends WebMapper<UserDto, User>   {
    User createUserDtoToEntity(CreateUserDto dto);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(RegisterReqDto dto);

    @Mapping(target = "image", expression = "java(\"/images/\" + user.getImage().getId())")
    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "phone", source = "user.phone")
    UserDto toDto (User user);

    @Mapping(target = "image", ignore = true)
    User toEntity(UserDto dto);
}
