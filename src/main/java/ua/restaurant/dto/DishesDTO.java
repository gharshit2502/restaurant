package ua.restaurant.dto;

import lombok.*;
import ua.restaurant.entity.Category;
import ua.restaurant.entity.Dishes;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DishesDTO {
    private List<Dishes> dishes;
}
