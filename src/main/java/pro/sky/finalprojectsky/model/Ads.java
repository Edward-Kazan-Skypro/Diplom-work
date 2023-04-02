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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    private String description;

    private Integer price;
    private String title;
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
    @OneToMany
    @JoinColumn(name = "comments")
    private List<Comment> comments;


}
