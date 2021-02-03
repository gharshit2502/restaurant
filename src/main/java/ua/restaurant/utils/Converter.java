package ua.restaurant.utils;

import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Converts entities to DTO objects
 */
public class Converter {

    public static List<DishDTO> dishesToDishesDTO(List<Dishes> list) {
        return list.stream()
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(ContextHelpers.isLocaleEnglish() ? d.getNameEn() : d.getNameUa())
                        .category(ContextHelpers.isLocaleEnglish()
                                ? d.getCategories().getCategoryEn() : d.getCategories().getCategoryUa())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<DishDTO> basketToDishesDTO(List<Baskets> list) {
        return list.stream()
                .map(Baskets::getDishes)
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(ContextHelpers.isLocaleEnglish() ? d.getNameEn() : d.getNameUa())
                        .category(ContextHelpers.isLocaleEnglish()
                                ? d.getCategories().getCategoryEn() : d.getCategories().getCategoryUa())
                        .build())
                .collect(Collectors.toList());
    }

    public static BigDecimal getTotalPrice(List<DishDTO> dishes) {
        return dishes.stream()
                .map(DishDTO::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static List<CategoryDTO> categoriesToCategoriesDTO(List<Categories> list) {
        return list.stream()
                .map(c -> CategoryDTO.builder()
                        .id(c.getId())
                        .category(ContextHelpers.isLocaleEnglish()
                                ? c.getCategoryEn() : c.getCategoryUa())
                        .build())
                .collect(Collectors.toList());
    }
}
