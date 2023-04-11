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

    //CreateUserDto toCreateUserDto(User entity);

    User createUserDtoToEntity(CreateUserDto dto);

    //@Mapping(source = "email", target = "username")
    //RegisterReqDto toDtoReg(User entity);

    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(source = "username", target = "email")
    User toEntity(RegisterReqDto dto);

    /*@Mapping(target = "image", expression = "java(\"/images/\" + entity.getImage().getId())")
    @Mapping(target = "image", qualifiedByName = "buildLink")
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "email", source = "entity.email")
    @Mapping(target = "firstName", source = "entity.firstName")
    @Mapping(target = "lastName", source = "entity.lastName")
    @Mapping(target = "phone", source = "entity.phone")
    UserDto toDto (User entity);

    @Named(value = "buildLink")
    default String buildLink(User user){
        return "/images/" + user.getImage().getId() + "/image";
    }*/
}
