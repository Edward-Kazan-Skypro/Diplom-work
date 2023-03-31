package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.model.FullAds;


@Mapper
public interface CreateAdsMapper {
    CreateAdsMapper INSTANCE = Mappers.getMapper(CreateAdsMapper.class );
    FullAds fromCreateAdstoFullAds (CreateAdsDto createAdsDto);
}