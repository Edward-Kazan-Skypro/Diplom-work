package pro.sky.finalprojectsky.model;

import lombok.*;
import pro.sky.finalprojectsky.dto.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //username = email
    @Email(regexp = ".+@.+[.]..+")//regulars
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private Role role;

    //здесь - связь между автором комментария и комментариями этого автора к этому объявлению
    @OneToMany(mappedBy = "user")
    private List<Comment> comment;

    //здесь - связь между аватаркой пользователя и пользователем
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}


