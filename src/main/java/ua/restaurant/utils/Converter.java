package ua.restaurant.utils;

import org.springframework.context.i18n.LocaleContextHolder;
import ua.restaurant.config.Localization;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Converter {

    public static List<DishDTO> dishesToDishesDTO(List<Dishes> list) {
        return list.stream()
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(Localization.isLocaleEnglish() ? d.getName_en() : d.getName_ua())
                        .category(Localization.isLocaleEnglish()
                                ? d.getCategories().getCategory_en() : d.getCategories().getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<DishDTO> basketToDishesDTO(List<Baskets> list) {
        return list.stream()
                .map(Baskets::getDishes)
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(Localization.isLocaleEnglish() ? d.getName_en() : d.getName_ua())
                        .category(Localization.isLocaleEnglish()
                                ? d.getCategories().getCategory_en() : d.getCategories().getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<CategoryDTO> categoriesToCategoriesDTO(List<Categories> list) {
        return list.stream()
                .map(c -> CategoryDTO.builder()
                        .id(c.getId())
                        .category(LocaleContextHolder.getLocale().equals(Locale.ENGLISH)
                                ? c.getCategory_en() : c.getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }
}
