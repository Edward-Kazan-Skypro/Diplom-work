package pro.sky.finalprojectsky.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob//large object binary
    @Type(type = "binary")
    private byte[] image;
    private Long fileSize;
    private String mediaType;
    private String title;

    @OneToOne (mappedBy = "image")
    private FullAds fullAds;
}
