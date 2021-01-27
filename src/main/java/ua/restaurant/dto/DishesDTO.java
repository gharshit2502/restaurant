package ua.restaurant.dto;

import lombok.*;
import ua.restaurant.entity.Dishes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
@ToString
public class DishesDTO {
    private List<DishDTO> dishes;
    private List<CategoryDTO> categories;

    public DishesDTO(List<Dishes> dishes, List<CategoryDTO> categories) {
        List<DishDTO> dishesList = new ArrayList<>();

        for (Dishes d : dishes) {
            dishesList.add(new DishDTO(d));
        }
        this.dishes = dishesList;
        this.categories = categories;
    }
}
