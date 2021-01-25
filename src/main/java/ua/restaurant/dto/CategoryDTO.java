package ua.restaurant.dto;

import lombok.*;
import org.springframework.context.i18n.LocaleContextHolder;
import ua.restaurant.entity.Category;

import static ua.restaurant.config.Constants.LANGUAGE_EN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDTO {
    private Long id;
    private String category;

    public CategoryDTO(Category c) {
        this.id = c.getId();
        if (LocaleContextHolder.getLocale().toString().equals(LANGUAGE_EN)) {
            this.category = c.getCategory_en();
        } else {
            this.category = c.getCategory_ua();
        }
    }
}
