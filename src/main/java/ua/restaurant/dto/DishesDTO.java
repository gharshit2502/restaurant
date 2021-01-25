package ua.restaurant.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DishesDTO {
    private List<DishDTO> dishes;
    private List<CategoryDTO> categories;
}
