package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.entity.Category;
import ua.restaurant.repository.DishesRepository;

import java.util.Arrays;

@Service
public class DishesService {
    private final DishesRepository dishesRepository;

    @Autowired
    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public DishesDTO getAllDishes() {
        DishesDTO dishes = new DishesDTO();
        dishes.setDishes(dishesRepository.findAll());
//        dishes.setCategories(Arrays.asList(Category.class.getEnumConstants().clone()));
        return dishes;
    }

//    public Optional<Login> findByUserLogin (LoginDTO loginDTO){
//        // TODO check for user availability. password check
//        return loginRepository.findByEmail(loginDTO.getEmail());
//    }

}
