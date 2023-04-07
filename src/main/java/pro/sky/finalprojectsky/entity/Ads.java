package pro.sky.finalprojectsky.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "ads")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User author;
    private int price;
    private String title;
    private String description;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;
}