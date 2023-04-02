package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.model.Image;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

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
            @Mapping(target = "image", qualifiedByName = "buildLinkToAvatarImage")
    })
    UserDto userToDto(User userEntity);

    @Named(value = "buildLinkToAvatarImage")
    default String buildLinkToAvatarImage(Image image) {
        return "/pictures/" + image.getTitle();
    }

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
    User dtoToEntity(UserDto userDto);
}
