package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishRepository;

import java.util.List;

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
        List<Dishes> dishes = dishRepository.findAll();
        return new DishesDTO(dishes, categoryService.findAll());
    }

}
