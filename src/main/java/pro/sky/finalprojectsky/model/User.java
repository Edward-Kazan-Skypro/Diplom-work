package pro.sky.finalprojectsky.model;

import lombok.*;
import pro.sky.finalprojectsky.dto.Role;
import javax.persistence.*;
import java.util.Collection;

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
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "second_name")
    private String secondName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "user_role")
    private Role role;

    //здесь - связь между автором комментария и комментариями этого автора к этому объявлению
    @OneToMany(mappedBy = "user")
    private Collection<Comment> comment;

    //здесь - связь между аватаркой пользователя и пользователем
    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
}


