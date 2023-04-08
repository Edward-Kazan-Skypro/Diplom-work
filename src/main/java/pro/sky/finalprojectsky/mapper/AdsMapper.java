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

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Mapper
public interface AdsMapper  {
    //extends WebMapper<AdsDto, Ads>
    //@Override
    //@Mapping(target = "author.id", source = "author")
    //@Mapping(target = "image", ignore = true)
    //@Mapping(source = "pk", target = "id")
    //Ads toEntity(AdsDto dto);

   /* @Override
    @Mapping(target = "author", source = "author.id")
    @Mapping(source = "id", target = "pk")
    @Mapping(target = "image", expression = "java(\"/ads/images/\" + entity.getImage().getId())")
    AdsDto toDto(Ads entity);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "id", ignore = true)
    Ads toEntity(CreateAdsDto dto);*/

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
                .pk(ads.getId())
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
                .pk(ads.getId())
                .price(ads.getPrice())
                .image(ads.getImage().getFilePath())
                .title(ads.getTitle())
                .build();
    }


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

    /*@Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "image", expression = "java(\"/ads/images/\" + entity.getImage().getId())")
    FullAdsDto toFullAdsDto(Ads entity);*/






}