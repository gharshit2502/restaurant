package ua.restaurant.dto;

import lombok.*;
import org.springframework.context.i18n.LocaleContextHolder;
import ua.restaurant.entity.Category;
import ua.restaurant.entity.Dish;

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

    public DishDTO(Dish d) {
        this.id = d.getId();
        this.price = d.getPrice();
        if (LocaleContextHolder.getLocale().toString().equals(LANGUAGE_EN)) {
            this.name = d.getName_en();
            this.category = d.getCategory().getCategory_en();
        } else {
            this.name = d.getName_ua();
            this.category = d.getCategory().getCategory_ua();
        }
    }
}
