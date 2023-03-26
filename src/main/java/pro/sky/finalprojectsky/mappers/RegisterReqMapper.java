package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.RegisterReqDto;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface RegisterReqMapper {
    RegisterReqMapper INSTANCE = Mappers.getMapper(RegisterReqMapper.class);
    RegisterReqDto userToDto (User userEntity);

    User dtoToEntity (RegisterReqDto registerRegDto);
}
