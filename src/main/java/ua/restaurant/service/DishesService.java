package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishesRepository;

import java.util.List;

@Service
public class DishesService {
    private final DishesRepository dishesRepository;
    private final CategoriesService categoriesService;

    @Autowired
    public DishesService(DishesRepository dishesRepository, CategoriesService categoriesService) {
        this.dishesRepository = dishesRepository;
        this.categoriesService = categoriesService;
    }

    public DishesDTO getAllDishes() {
        List<Dishes> dishes = dishesRepository.findAll();
        return new DishesDTO(dishes, categoriesService.findAll());
    }

}
