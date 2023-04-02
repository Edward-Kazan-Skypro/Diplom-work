package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import pro.sky.finalprojectsky.dto.CommentDto;

import pro.sky.finalprojectsky.entity.AdsComment;


@Mapper
public interface CommentMapper extends WebMapper<CommentDto, AdsComment> {

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

/* @Mapping(target = "author", source = "author.id")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "createdAt", source = "createdAt")*/

/*@Mapping(target = "author", ignore = true)
    @Mapping(target = "id", source = "pk")
    @Mapping(target = "createdAt", source = "createdAt")*/

   /* @Mappings({
            //field - authorId
            @Mapping(target = "authorId", source = "userEntity.id"),
            //field - authorFirstName
            @Mapping(target = "authorFirstName", source = "userEntity.firstName"),
            //field - authorAvatarURL (аватарка пользователя, автора комментария)
            @Mapping(target = "authorAvatarURL", source = "userEntity.avatarURL"),
            //field - createdAt
            @Mapping(target = "createdAt", source = "commentEntity.createdAt"),
            //field - commentId
            @Mapping(target = "commentId", source = "commentEntity.id"),
            //field - text
            @Mapping(target = "text", source = "commentEntity.text")
    })*/
//CommentDto commentToDto (Comment commentEntity, User userEntity);

   /* @Mappings({
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
    */