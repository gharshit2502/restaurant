package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.config.Localization;
import ua.restaurant.dto.CategoriesDTO;
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
public class DishService {
    private final DishRepository dishRepository;
    private final CategoryService categoryService;

    @Autowired
    public DishService(DishRepository dishRepository, CategoryService categoryService) {
        this.dishRepository = dishRepository;
        this.categoryService = categoryService;
    }

    public DishesDTO getAllDishes() {
        DishesDTO dishes = new DishesDTO();

        List<DishDTO> listDTO = new ArrayList<>();
        for (Dish d : dishRepository.findAll()) {
            listDTO.add(new DishDTO(d));
        }

        dishes.setDishes(listDTO);
        dishes.setCategories(categoryService.findAll());

//        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
        return dishes;
    }

//    public Optional<Login> findByUserLogin (LoginDTO loginDTO){
//        // TODO check for user availability. password check
//        return loginRepository.findByEmail(loginDTO.getEmail());
//    }

    private List<DishDTO> getDishesDTO(List<Dish> list) {
        List<DishDTO> dishesDTO = new ArrayList<>();

        for (Dish d : list) {
            dishesDTO.add(new DishDTO(d));
        }
        return dishesDTO;
    }

}
