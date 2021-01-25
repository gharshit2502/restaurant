package ua.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private final CategoryService categoryService;
    @Autowired
    public MainController(DishService dishService, CategoryService categoryService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public DishesDTO getMain() {
//        log.info("{}", loginService.getAllUsers());

        System.out.println(LocaleContextHolder.getLocale());
        LocaleContextHolder.getLocale().toString().equals(LANGUAGE_EN);

        return dishService.getAllDishes();
    }

//    @GetMapping("/getCat")
//    public CategoriesDTO getCat() {
//        return categoryService.getAllCategories();
//    }

}
