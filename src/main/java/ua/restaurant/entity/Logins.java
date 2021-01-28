package ua.restaurant.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Data
@Entity
@Table( name="login",
        uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Logins {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(nullable = false)
    private String email;

    @Column(name = "role")
//    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private RoleType role;
    private Timestamp time;
}
