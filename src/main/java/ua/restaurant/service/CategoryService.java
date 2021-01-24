package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.config.Localization;
import ua.restaurant.dto.CategoriesDTO;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.entity.Category;
import ua.restaurant.entity.Dish;
import ua.restaurant.repository.CategoryRepository;
import ua.restaurant.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

import static ua.restaurant.config.Constants.LANGUAGE_EN;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoriesDTO getAllCategories() {
        CategoriesDTO categories = new CategoriesDTO();
        List<CategoryDTO> result = new ArrayList<>();
        List<Category> category = categoryRepository.findAll();
        for (Category d : category) {
            result.add(getCategoryDTO(d));
        }
//        DishesDTO dishesDTO = new DishesDTO();
//        dishesDTO.setDishes(result);
////        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
//        return dishesDTO;

        categories.setCategories(result);
//        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
        return categories;
    }

//    public Optional<Login> findByUserLogin (LoginDTO loginDTO){
//        // TODO check for user availability. password check
//        return loginRepository.findByEmail(loginDTO.getEmail());
//    }

    private CategoryDTO getCategoryDTO(Category c) {
        if (Localization.getLang().equals(LANGUAGE_EN)) {
            return CategoryDTO.builder()
                    .id(c.getId())
                    .category(c.getCategory_en())
                    .build();
        }
        return CategoryDTO.builder()
                .id(c.getId())
                .category(c.getCategory_ua())
                .build();
    }

}
