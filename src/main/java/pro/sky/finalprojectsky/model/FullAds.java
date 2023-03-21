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
    private Integer pk;
    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    private String phone;

    private Integer price;
    private String title;

    //здесь - связь между объявлением и списком комментариев
    @OneToMany(mappedBy = "fullAds")
    private Collection<Comment> comments;

    //здесь - связь между объявлением и картинками к этому объявлению
    @OneToMany(mappedBy = "fullAds")
    private Collection<Image> images;

}
