package pro.sky.finalprojectsky.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "comments")
public class Comment   {
    //id комментария
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    @ManyToOne (fetch = FetchType.LAZY)
    //id автора комментария
    @JoinColumn(name = "author_id")
    private User user;
    //ссылка на аватар автора комментария
    @OneToOne
    @JoinColumn(name = "author_image_id")
    private Image image;

    //дата и время создания комментария
    private Long createdAt;

    //текст комментария
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    private Ads ads;
}

