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
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob//large object bynary
    @Type(type = "binary")
    private byte[] image;
    private Long fileSize;
    private String mediaType;


}
