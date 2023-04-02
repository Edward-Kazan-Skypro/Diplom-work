package pro.sky.finalprojectsky.model;

import lombok.*;
import pro.sky.finalprojectsky.dto.Role;
import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Column(name = "author")
    private Integer id;
    @Email(regexp = ".+@.+[.]..+")//regulars
    @Column(name = "email_as_userName")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany
    @JoinColumn(name = "ads")
    private List<Ads> adsList;
    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar image;
}