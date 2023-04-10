package pro.sky.finalprojectsky.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


/**
 * Class of Ads (advertisement/объявление).
 */
@Entity
@Table(name = "ads")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class Ads {
    /**
     * "ID/ id обьявления" field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * "users/пользователь" field
     */
    @ManyToOne
    private User author;
    /**
     * "price/цена" field
     */
    private int price;
    /**
     * "title/заголовок" field
     */
    private String title;
    /**
     * "description/описание обьявления" field
     */
    private String description;
    /**
     * "image/изображение" field
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ads")
    private Image image;

}