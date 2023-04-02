package pro.sky.finalprojectsky.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.model.FullAds;

import java.util.Collection;
/**
 * Interface of ads mapper
 */
@Mapper
public interface AdsMapper extends WebMapper<AdsDto, Ads>{

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    //imageURL
    @Mapping(target = "image", ignore = true)
    AdsDto adsToAdsDto(FullAds fullAds);

    @Mapping(target = "image", ignore = true)
    FullAds adsDtoToAds(AdsDto adsDto);

    @Mapping(target = "image", ignore = true)
    FullAdsDto fullAdsToFullAdsDto(FullAds fullAds);
    @Mapping(target = "image", ignore = true)
    FullAds fullAdsDtoToFullAds(FullAdsDto fullAdsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    FullAds createAdsToAds(CreateAdsDto createAdsDto);

    Collection<AdsDto> adsCollectionToAdsDto(Collection<FullAds> adsCollection);


}
