package ua.restaurant.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class Baskets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loginId", referencedColumnName = "id")
    private Logins login;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishId", referencedColumnName = "id")
    private Dishes dishes;
}
