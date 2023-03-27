package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.User;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mappings({
            //field - authorId
            @Mapping(target = "authorId", source = "userEntity.id"),
            //field - authorFirstName
            @Mapping(target = "authorFirstName", source = "userEntity.firstName"),
            //field - authorImage (аватарка пользователя, автора комментария)
            @Mapping(target = "authorImageURL", source = "userEntity.avatarURL"),
            //field - createdAt
            @Mapping(target = "createdAt", source = "commentEntity.createdAt"),
            //field - commentId
            @Mapping(target = "commentId", source = "commentEntity.id"),
            //field - text
            @Mapping(target = "text", source = "commentEntity.text")
    })
    CommentDto commentToDto (Comment commentEntity, User userEntity);

    @Mappings({
            //field - authorId
            @Mapping(target = "authorId", source = "commentDto.authorId"),
            //field - authorFirstName
            @Mapping(target = "authorFirstName", source = "commentDto.authorFirstName"),
            //field - authorImage (аватарка пользователя, автора комментария)
            @Mapping(target = "authorImageURL", source = "commentDto.authorImageURL"),
            //field - createdAt
            @Mapping(target = "createdAt", source = "commentDto.createdAt"),
            //field - commentId
            //@Mapping(target = "commentId", source = "commentDto.id"),
            //field - text
            @Mapping(target = "text", source = "commentDto.text")
    })
    Comment commentToEntity (CommentDto commentDto);
}

/* @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")*/

/*@Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", source = "createdAt")*/

