package pro.sky.finalprojectsky.entity;

import lombok.*;
import pro.sky.finalprojectsky.dto.Role;
import javax.persistence.*;


/**
 * Class of User (user/пользователь).
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class User {
    /**
     * "ID/id пользователя" field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * "email" field
     */
    private String email;
    /**
     * "password/пароль" field
     */
    private String password;
    /**
     * "firstName/имя" field
     */
    private String firstName;
    /**
     * "lastName/фамилия" field
     */
    private String lastName;
    /**
     * "phone/номер телефона" field
     */
    private String phone;
    /**
     * "role/тип пользователя" (USER, ADMIN) field
     */
    @Enumerated(EnumType.STRING)
    private Role role;
}