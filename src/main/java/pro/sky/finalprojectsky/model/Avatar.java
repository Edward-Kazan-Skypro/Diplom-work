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
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob//large object binary
    @Type(type = "binary")
    private byte[] image;
    private Long fileSize;
    private String mediaType;

    private String title;

    //здесь - связь между автаркой и пользователем
    //@OneToOne (mappedBy = "avatar")
    //private User user;
}
