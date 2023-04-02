package pro.sky.finalprojectsky.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.model.Ads;
import pro.sky.finalprojectsky.model.Image;
import pro.sky.finalprojectsky.model.User;


@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mappings({
            @Mapping(target = "image", qualifiedByName = "buildLinkToImage"),
            @Mapping(target = "author", qualifiedByName = "buildLinkToAuthor")
    })
    AdsDto toDto(Ads ads);

    @Named(value = "buildLinkToImage")
    default String buildLinkToImage(Image image) {
        return "/ads/" + image.getId() + "/image";
    }

    @Named(value = "buildLinkToAuthor")
    default Integer buildLinkToAuthor(User author) {
        return author.getId();
    }

    @Mappings({
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "author", ignore = true)
    })
    Ads adsDtoToAds(AdsDto adsDto);
}