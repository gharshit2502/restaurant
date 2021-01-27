package ua.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.restaurant.dto.CategoriesDTO;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.service.CategoryService;
import ua.restaurant.service.DishService;

import static ua.restaurant.config.Constants.LANGUAGE_EN;


@RestController
@RequestMapping(value = "api/")
public class MainController {
    private final DishService dishService;

    @Autowired
    public MainController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/get")
    public DishesDTO getMain() {
        return dishService.getAllDishes();
    }

}
