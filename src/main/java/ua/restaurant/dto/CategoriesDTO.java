package ua.restaurant.dto;

import lombok.*;
import ua.restaurant.entity.Categories;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@Builder
@ToString
public class CategoriesDTO {
    private List<CategoryDTO> categories;

    public CategoriesDTO(List<Categories> categories) {
        this.categories = new ArrayList<>();

        for (Categories c : categories) {
            this.categories.add(new CategoryDTO(c));
        }
    }
}
