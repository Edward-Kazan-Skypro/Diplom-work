package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.LoginReqDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface LoginReqMapper {
    LoginReqMapper INSTANCE = Mappers.getMapper(LoginReqMapper.class);
    LoginReqDto userToDto (User userEntity);
    User dtoToEntity (LoginReqDto loginReqDto);
}
