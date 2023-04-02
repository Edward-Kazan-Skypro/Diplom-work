package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.model.Ads;
import pro.sky.finalprojectsky.model.Image;

@Mapper
public interface FullAdsMapper {
    FullAdsMapper INSTANCE = Mappers.getMapper(FullAdsMapper.class);

    @Mapping(target = "image", qualifiedByName = "buildLinkToImage")
    FullAdsDto adsToFullAdsDto(Ads ads);
    @Named(value = "buildLinkToImage")
    default String buildLinkToImage(Image image) {
        return "/ads/" + image.getId() + "/image";
    }
    @Mapping(target = "image", ignore = true)
    Ads fullAdsDtoToFullAds(FullAdsDto fullAdsDto);
}

