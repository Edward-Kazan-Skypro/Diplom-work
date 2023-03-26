package pro.sky.finalprojectsky.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.model.FullAds;

import java.util.Collection;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    AdsDto adsToAdsDto(FullAds fullAds);

    FullAds adsDtoToAds(AdsDto adsDto);

    FullAdsDto fullAdsToFullAdsDto(FullAds fullAds);
    FullAds fullAdsDtoToFullAds(FullAdsDto fullAdsDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    FullAds createAdsToAds(CreateAdsDto createAdsDto);

    Collection<AdsDto> adsCollectionToAdsDto(Collection<FullAds> adsCollection);


}
