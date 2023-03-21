package pro.sky.finalprojectsky.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity(name = "comments")
public class Comment   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String createdAt;

    private String text;

    //здесь - связь между автором комментария и самим комментарием
    @ManyToOne (fetch = FetchType.LAZY)
    //добавляемую колонку назвал author_id,
    //т.к. по смыслу это id пользователя (автора), который оставил комментарий
    @JoinColumn(name = "author_id")
    private User user;

    //здесь - связь между объявлением и комментарием к нему
    //много комментариев относятся к одному объявлению
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fullAds_id")
    private FullAds fullAds;

    //private Integer author;
    //Это поле и есть user_id и тоже имеет тип Integer
    //Поэтому добавлена связь с таблицей users,
    //чтобы оттуда получать id пользователя (user), который оставляет комментарий

}

