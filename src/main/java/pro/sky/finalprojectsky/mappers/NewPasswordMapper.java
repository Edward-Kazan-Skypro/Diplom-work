package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.NewPasswordDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface NewPasswordMapper {
    NewPasswordMapper INSTANCE = Mappers.getMapper(NewPasswordMapper.class);

   @Mappings({
            @Mapping(target = "newPassword", ignore = true),
            @Mapping(target = "currentPassword", ignore = true)
    })
    NewPasswordDto userToDto(User userEntity);

    @Mapping(target = "password", source = "newPassword")
    User dtoToEntity(NewPasswordDto newPasswordDto);
}
