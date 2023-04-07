package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;

@Mapper
public interface AdsMapper extends WebMapper<AdsDto, Ads> {
    @Override
    @Mapping(target = "author.id", source = "author")
    @Mapping(target = "image", ignore = true)
    @Mapping(source = "pk", target = "id")
    Ads toEntity(AdsDto dto);

    @Override
    @Mapping(target = "author", source = "author.id")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "image", expression = "java(\"/ads/images/\" + entity.getImage().getId())")
    AdsDto toDto(Ads entity);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    Ads toEntity(CreateAdsDto dto);

    /*@Named(value = "getUserId")
    default Integer getUserId (User user){
        return user.getId();
    }

    @Named(value = "getImageId")
    default Integer getImageId (Image image){
        return image.getId();
    }

    @Named(value = "getAdsId")
    default Integer getAdsId (Ads ads){
        return ads.getId();
    }*/

    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", expression = "java(\"/ads/images/\" + entity.getImage().getId())")
    FullAdsDto toFullAdsDto(Ads entity);
}