package pro.sky.finalprojectsky.entity;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "ads_comment")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class AdsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User author;
    private long createdAt;
    private String text;
    @ManyToOne
    private Ads ads;
}