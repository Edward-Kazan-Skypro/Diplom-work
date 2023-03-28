package pro.sky.finalprojectsky.model;

import lombok.*;
import pro.sky.finalprojectsky.dto.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
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

    //Если исходить из фронта, то при заполнении поля userName запрашивается ввод email
    //т.е. userName = email
    @Email(regexp = ".+@.+[.]..+")//regulars
    @Column(name = "email_as_userName")
    private String emailAsUserName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar")
    private String avatarURL;
    @Column(name = "user_role")
    private Role role;
}


