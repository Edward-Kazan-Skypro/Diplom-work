package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;

/**
 * Interface of ads comments mapper
 */
@Mapper
public interface AdsCommentMapper extends WebMapper<AdsCommentDto, AdsComment> {

    @Override
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AdsComment toEntity(AdsCommentDto dto);

    @Override
    @Mapping(target = "authorImage",
            expression = "java(\"/users/images/\" + entity.getAuthor().getImage().getId())")
    @Mapping(target = "author", source = "author.id")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "createdAt", source = "entity.createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    AdsCommentDto toDto(AdsComment entity);
}