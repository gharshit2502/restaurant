package ua.restaurant.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DishDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String category;
}
