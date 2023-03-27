package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface RegisterReqMapper {
    RegisterReqMapper INSTANCE = Mappers.getMapper(RegisterReqMapper.class);

    @Mappings({
            @Mapping(target = "username", source = "userName"),
            @Mapping(target = "lastName", source = "secondName")
    })
    RegisterReqDto userToDto (User userEntity);

    @Mappings({
            @Mapping(target = "userName", source = "username"),
            @Mapping(target = "secondName", source = "lastName")
    })
    User dtoToEntity (RegisterReqDto registerRegDto);
}
