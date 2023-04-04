package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.finalprojectsky.dto.CreateUserDto;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.User;

@Mapper
public interface UserMapper extends WebMapper<UserDto, User> {
    CreateUserDto toCreateUserDto(User entity);

    User createUserDtoToEntity(CreateUserDto dto);

    @Mapping(source = "email", target = "username")
    RegisterReqDto toDtoRegReq(User entity);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(RegisterReqDto dto);
}