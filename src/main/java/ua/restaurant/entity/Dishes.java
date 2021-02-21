package ua.restaurant.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

@Entity
public class Dishes {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Pattern(regexp = "^[a-zA-z0-9 -]+$",
            message = "{error.dish.name_en}")
    @Column(name = "nameEn")
    private String nameEn;

    @Pattern(regexp = "^[А-ЩЬЮЯҐЄІЇа-щьюяґєії0-9 \\'-]+$",
            message = "{error.dish.name_ua}")
    @Column(name = "nameUa")
    private String nameUa;

    @Min(value = 1/100L, message = "{error.dish.price}")
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Categories categories;
    private LocalDateTime time;
}
