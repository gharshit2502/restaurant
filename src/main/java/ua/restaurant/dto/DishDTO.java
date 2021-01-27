package ua.restaurant.dto;

import lombok.*;
import org.springframework.context.i18n.LocaleContextHolder;
import ua.restaurant.entity.Dishes;

import java.math.BigDecimal;
import static ua.restaurant.config.Constants.LANGUAGE_EN;

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

    public DishDTO(Dishes d) {
        this.id = d.getId();
        this.price = d.getPrice();
        if (LocaleContextHolder.getLocale().toString().equals(LANGUAGE_EN)) {
            this.name = d.getName_en();
            this.category = d.getCategories().getCategory_en();
        } else {
            this.name = d.getName_ua();
            this.category = d.getCategories().getCategory_ua();
        }
    }
}
