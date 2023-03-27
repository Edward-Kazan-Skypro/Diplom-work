package pro.sky.finalprojectsky.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity(name = "fullAds")
public class FullAds   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private Integer price;
    private String title;
    private String imageURL;

    //здесь - связь между объявлением и списком комментариев
    //Вопрос целесообразности этой связи?
    //Мы же формируем полный текст объявления используя маппинг полей из AdsDto и UserDto
}
