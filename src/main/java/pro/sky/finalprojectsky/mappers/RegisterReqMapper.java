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
            //username
            @Mapping(target = "emailAsUserName", source = "userEntity.emailAsUserName"),
            //password
            @Mapping(target = "password", source = "userEntity.password"),
            //firstName
            @Mapping(target = "firstName", source = "userEntity.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "userEntity.lastName"),
            //phone
            @Mapping(target = "phone", source = "userEntity.phone")
    })
    RegisterReqDto userToDto (User userEntity);

    @Mappings({
            //emailAsUserName
            @Mapping(target = "emailAsUserName", source = "registerRegDto.emailAsUserName"),
            //password
            @Mapping(target = "password", source = "registerRegDto.password"),
            //firstName
            @Mapping(target = "firstName", source = "registerRegDto.firstName"),
            //lastName
            @Mapping(target = "lastName", source = "registerRegDto.lastName"),
            //phone
            @Mapping(target = "phone", source = "registerRegDto.phone")
    })
    User dtoToEntity (RegisterReqDto registerRegDto);
}
