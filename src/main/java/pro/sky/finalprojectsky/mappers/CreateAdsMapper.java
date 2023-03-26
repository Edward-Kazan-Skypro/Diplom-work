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

    //Здесь - из полного объявления (FullAds) создаем объект CreateAdsDto
    //Укажем, из каких полей источника в какие поля будут передаваться данные
    //т.е. target = "description" - это КУДА передаются данные из объекта FullAds
    //а source = "fullAds.description" - это ОТКУДА передаются данные
    //Обратите внимание !!
    //source = "fullAds.description" и CreateAdsDto fullAdsToCreateAdsDto (FullAds fullAds)
    //в обоих местах указано fullAds
  /* @Mappings({
            @Mapping(target = "description", source = "fullAds.description"),
            @Mapping(target = "price", source = "fullAds.price"),
            @Mapping(target = "title", source = "fullAds.title")
    })
   CreateAdsDto fullAdsToCreateAdsDto (FullAds fullAds);*/

    //Здесь - из CreateAdsDto передаем данные в FullAds
    //Маппинг полей - по аналогии с методом выше.
    /*@Mappings({
            @Mapping(target = "description", source = "createAdsDto.description"),
            @Mapping(target = "price", source = "createAdsDto.price"),
            @Mapping(target = "title", source = "createAdsDto.title")
    })*/


//ВАЖНО !!!
//Имплементация данного интерфейса - автоматическая (см. значки в IDE слева от методов)

