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
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    //имя создателя комментария
    //не понял как сделать еще одну связь к сущности User,
    //чтобы получать оттуда authorFirstName
    private String authorFirstName;

    //дата и время создания комментария
    private Long createdAt;

    //текст комментария
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fullAds_id")
    private Ads ads;
}

