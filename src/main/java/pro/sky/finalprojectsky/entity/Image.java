package pro.sky.finalprojectsky.entity;

import lombok.*;
import org.hibernate.annotations.Type;
import javax.persistence.*;


/**
 * Class of Image (advertisement image/изображение в объявлениях).
 */
@Entity
@Table(name = "image")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Lob
    @Type(type = "binary")
    private byte[] image;
    private long fileSize;
    private String mediaType;
    private String filePath;
    @OneToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String toString() {
        return "AdsEntity(id=" + this.getId() + ", image=" + java.util.Arrays.toString(this.getImage()) + ")";
    }
}