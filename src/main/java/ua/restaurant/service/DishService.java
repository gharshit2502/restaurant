package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.config.Localization;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.entity.Dish;
import ua.restaurant.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;

import static ua.restaurant.config.Constants.LANGUAGE_EN;

@Service
public class DishService {
    private final DishRepository dishRepository;

    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public DishesDTO getAllDishes() {
//        List<DishDTO> result = new ArrayList<>();
//        List<Dish> dishes = dishRepository.findAll();
//        for (Dish d : dishes) {
//            result.add(getDishDTO(d));
//        }
//        DishesDTO dishesDTO = new DishesDTO();
//        dishesDTO.setDishes(result);
////        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
//        return dishesDTO;

        DishesDTO dishes = new DishesDTO();
        dishes.setDishes(dishRepository.findAll());
//        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
        return dishes;
    }

//    public Optional<Login> findByUserLogin (LoginDTO loginDTO){
//        // TODO check for user availability. password check
//        return loginRepository.findByEmail(loginDTO.getEmail());
//    }

    private DishDTO getDishDTO(Dish d) {
        if (Localization.getLang().equals(LANGUAGE_EN)) {
            return DishDTO.builder()
                    .id(d.getId())
                    .name(d.getName_en())
                    .price(d.getPrice())
                    .category(d.getCategory().getCategory_en())
                    .build();
        }
        return DishDTO.builder()
                .id(d.getId())
                .name(d.getName_ua())
                .price(d.getPrice())
                .category(d.getCategory().getCategory_ua())
                .build();
    }

}
