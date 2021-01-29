package ua.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loginId", referencedColumnName = "id")
    private Logins login;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "orderListId", referencedColumnName = "id")
//    private OrdersReceipts orderList;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
