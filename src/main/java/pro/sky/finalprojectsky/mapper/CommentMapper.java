package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;

@Mapper
public interface CommentMapper extends WebMapper<CommentDto, AdsComment>{
    @Override
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AdsComment toEntity(CommentDto dto);

    @Override
    @Mapping(target = "author", source = "author.id")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "createdAt", source = "entity.createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    CommentDto toDto(AdsComment entity);
}
