package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.CreateUserDto;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.User;
/**
 * Interface of user mapper
 */
@Mapper
public interface UserMapper extends WebMapper<UserDto, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    CreateUserDto toCreateUserDto(User entity);

    User createUserDtoToEntity(CreateUserDto dto);

    @Mapping(source = "email", target = "username")
    RegisterReqDto toDtoRegReq(User entity);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    org.springframework.security.core.userdetails.User toEntity(RegisterReqDto dto);

   @Mappings({
            //id
            @Mapping(target = "id", source = "userEntity.id"),
            //email = userName
            @Mapping(target = "email", source = "userEntity.email"),
            //firstName
            @Mapping(target = "firstName", source = "userEntity.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "userEntity.lastName"),
            //phone
            @Mapping(target = "phone", source = "userEntity.phone"),
            //imageURL
            @Mapping(target = "image", ignore = true)
    })
    UserDto userToDto (User userEntity);

   @Mappings({
            //id - не маппим, т.к. у сущности авто получение id

            //email = userName
            @Mapping(target = "email", source = "userDto.email"),
            //firstName
            @Mapping(target = "firstName", source = "userDto.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "userDto.lastName"),
            //phone
            @Mapping(target = "phone", source = "userDto.phone"),
            //imageURL
            @Mapping(target = "image", ignore = true)
    })
    User dtoToEntity (UserDto userDto);
}
