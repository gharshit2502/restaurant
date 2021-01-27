package ua.restaurant.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loginId", referencedColumnName = "id")
    private Login login;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderListId", referencedColumnName = "id")
    private OrdersReceipts orderList;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Timestamp time;
}
