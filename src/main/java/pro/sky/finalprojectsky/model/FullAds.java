package pro.sky.finalprojectsky.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "fullAds")
public class FullAds   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

    //здесь - связь между объявлением и картинками к этому объявлению
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private String phone;

    private Integer price;

    private String title;

    //здесь - связь между объявлением и списком комментариев
    @OneToMany(mappedBy = "fullAds")
    private List<Comment> comments;
}
