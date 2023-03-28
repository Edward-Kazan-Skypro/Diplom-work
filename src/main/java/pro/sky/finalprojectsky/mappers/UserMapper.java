package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            //id
            @Mapping(target = "id", source = "userEntity.id"),
            //emailAsUserName
            @Mapping(target = "emailAsUserName", source = "userEntity.emailAsUserName"),
            //firstName
            @Mapping(target = "firstName", source = "userEntity.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "userEntity.lastName"),
            //phone
            @Mapping(target = "phone", source = "userEntity.phone"),
            //avatarURL
            @Mapping(target = "avatarURL", source = "userEntity.avatarURL")
    })
    UserDto userToDto (User userEntity);

    @Mappings({
            //id - не маппим, т.к. у сущности авто получение id

            //emailAsUserName
            @Mapping(target = "emailAsUserName", source = "userDto.emailAsUserName"),
            //firstName
            @Mapping(target = "firstName", source = "userDto.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "userDto.lastName"),
            //phone
            @Mapping(target = "phone", source = "userDto.phone"),
            //avatarURL
            @Mapping(target = "avatarURL", source = "userDto.avatarURL")
    })
    User dtoToEntity (UserDto userDto);
}
