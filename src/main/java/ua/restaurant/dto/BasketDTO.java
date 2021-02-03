package ua.restaurant.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class BasketDTO {
    private List<DishDTO> dishes;
    private BigDecimal totalPrice;
}
