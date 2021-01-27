package ua.restaurant.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Baskets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loginId", referencedColumnName = "id")
    private Login login;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishId", referencedColumnName = "id")
    private Dishes dishes;
}
