package pro.sky.finalprojectsky.mappers;

import org.mapstruct.Mapper;


@Mapper
public interface CreateAdsMapper {

}

    /*//Обязательно обозначаем Инстанс!
    CreateAdsMapper INSTANCE = Mappers.getMapper(CreateAdsMapper.class );

    //Здесь - из полного объявления (FullAds) создаем объект CreateAdsDto
    //Укажем, из каких полей источника в какие поля будут передаваться данные
    //т.е. target = "description" - это КУДА передаются данные из объекта FullAds
    //а source = "fullAds.description" - это ОТКУДА передаются данные
    //Обратите внимание !!
    //source = "fullAds.description" и CreateAdsDto fullAdsToCreateAdsDto (FullAds fullAds)
    //в обоих местах указано fullAds
   *//* @Mappings({
            @Mapping(target = "description", source = "fullAds.description"),
            @Mapping(target = "price", source = "fullAds.price"),
            @Mapping(target = "title", source = "fullAds.title")
    })*//**//*
    CreateAdsDto fullAdsToCreateAdsDto (FullAds fullAds);

    //Здесь - из CreateAdsDto передаем данные в FullAds
    //Маппинг полей - по аналогии с методом выше.
    *//**//*@Mappings({
            @Mapping(target = "createAdsDescription", source = "description"),
            @Mapping(target = "createAdsDto.createAdsPrice", source = "price"),
            @Mapping(target = "createAdsDto.createAdsTitle", source = "title")
    })
    FullAds fromCreateAdstoFullAds (CreateAdsDto createAdsDto);
}

//ВАЖНО !!!
//Имплементация данного интерфейса - автоматическая (см. значки в IDE слева от методов)

*/