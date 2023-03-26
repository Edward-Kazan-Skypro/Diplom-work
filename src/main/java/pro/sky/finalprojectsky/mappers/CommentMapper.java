package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.model.Comment;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

   /* @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")*/
    CommentDto commentToDto (Comment commentEntity);

    /*@Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", source = "createdAt")*/
    Comment commentToEntity (CommentDto commentDto);

}

