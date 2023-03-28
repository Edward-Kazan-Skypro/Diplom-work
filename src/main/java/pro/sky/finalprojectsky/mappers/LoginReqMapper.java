package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.LoginReqDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface LoginReqMapper {
    LoginReqMapper INSTANCE = Mappers.getMapper(LoginReqMapper.class);

    @Mappings({
            @Mapping(target = "password", source = "userEntity.password"),
            @Mapping(target = "emailAsUserName", source = "userEntity.emailAsUserName")
    })
    LoginReqDto userToDto (User userEntity);

    @Mappings({
            @Mapping(target = "password", source = "loginReqDto.password"),
            @Mapping(target = "emailAsUserName", source = "loginReqDto.emailAsUserName")
    })
    User dtoToEntity (LoginReqDto loginReqDto);
}
