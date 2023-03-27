package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto userToDto (User userEntity);

    User dtoToEntity (UserDto userDto);
}
