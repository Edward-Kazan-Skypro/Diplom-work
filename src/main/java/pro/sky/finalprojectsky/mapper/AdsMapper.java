package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;


@Mapper
public interface AdsMapper  {
    default Ads convertCreateAdsDtoToEntity(CreateAdsDto dto, User user, Image image) {
        return Ads
                .builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .author(user)
                .image(image)
                .build();
    }

    default AdsDto convertEntityToAdsDto(Ads ads){
        return AdsDto
                .builder()
                .id(ads.getId())
                .author(ads.getAuthor().getId())
                .image(ads.getImage().getFilePath())
                .price(ads.getPrice())
                .description(ads.getDescription())
                .build();
    }

    default FullAdsDto convertEntityToFullAdsDto(Ads ads){
        return FullAdsDto
                .builder()
                .authorFirstName(ads.getAuthor().getFirstName())
                .authorLastName(ads.getAuthor().getLastName())
                .description(ads.getDescription())
                .email(ads.getAuthor().getEmail())
                .phone(ads.getAuthor().getPhone())
                .id(ads.getId())
                .price(ads.getPrice())
                .image(ads.getImage().getFilePath())
                .title(ads.getTitle())
                .build();
    }
}