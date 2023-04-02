package pro.sky.finalprojectsky.model;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //здесь - связь между объявлением и автором этого объявления
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    private String description;

    //здесь - связь между объявлением и картинками к этому объявлению
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    private Integer price;

    private String title;

    //здесь - связь между объявлением и списком комментариев
    @OneToMany(mappedBy = "ads")
    private List<Comment> comments;
}
