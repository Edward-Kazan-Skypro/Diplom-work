package pro.sky.finalprojectsky.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Data
@Entity(name = "comments")
public class Comment   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int authorId;
    private String authorFirstName;
    private String authorImageURL;
    private Timestamp createdAt;
    private String text;
}

