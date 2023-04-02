package pro.sky.finalprojectsky.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.model.Ads;
import pro.sky.finalprojectsky.model.Image;
import pro.sky.finalprojectsky.model.User;

import java.util.Collection;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    //Маппим поля для получения ДТО из сущности
    @Mappings({
            @Mapping(target = "id", source = "ads.id"),
            @Mapping(target = "author", qualifiedByName = "getIdFromUser"),
            @Mapping(target = "image", qualifiedByName = "buildLinkToImage"),
            @Mapping(target = "price", source = "ads.price"),
            @Mapping(target = "title", source = "ads.title")
    })
    AdsDto adsToAdsDto(Ads ads);
    @Named(value = "buildLinkToImage")
    default String buildLinkToImage(Image image) {
        return "/pictures/" + image.getTitle();
    }

    /*В получении сущности из AdsDto - нет необходимости
    @Mappings({
            //@Mapping(target = "id", source = "ads.id"),
            @Mapping(target = "author", source = "ads.author"),
            @Mapping(target = "image", qualifiedByName = "buildLinkToImage"),
            @Mapping(target = "price", source = "ads.price"),
            @Mapping(target = "title", source = "ads.title")
    })
    Ads adsDtoToAds(AdsDto adsDto);*/

    //Получаем полное объявление
    @Mappings({
            @Mapping(target = "id", source = "ads.id"),
            //@Mapping(target = "author", qualifiedByName = "getIdFromUser"),
            @Mapping(target = "authorFirstName", source = "user.firstName"),
            @Mapping(target = "authorLastName", source = "user.lastName"),
            @Mapping(target = "phone", source = "user.phone"),
            @Mapping(target = "email", source = "user.email"),
            @Mapping(target = "image", ignore = true),
            @Mapping(target = "price", source = "ads.price"),
            @Mapping(target = "title", source = "ads.title")    })
    FullAdsDto asToFullAdsDto(Ads ads, User user, Image image);

    @Named(value = "getIdFromUser")
    default Integer getIdFromUser(User user) {
        return user.getId();
    }
    @Named(value = "getAuthorFirstNameFromUser")
    default String getAuthorFirstNameFromUser(User user) {
        return user.getFirstName();
    }
    @Named(value = "getAuthorLastNameFromUser")
    default String getAuthorLastNameFromUser(User user) {
        return user.getLastName();
    }
    @Named(value = "getPhoneFromUser")
    default String getPhoneFromUser(User user) {
        return user.getPhone();
    }
    @Named(value = "getEmailFromUser")
    default String getEmailFromUser(User user) {
        return user.getEmail();
    }

    //В получении сущности из FullAdsDto - нет необходимости


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Ads createAdsToAds(CreateAdsDto createAdsDto);

    Collection<AdsDto> adsCollectionToAdsDto(Collection<Ads> adsCollection);
}
