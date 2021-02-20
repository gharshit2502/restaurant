package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;
import ua.restaurant.service.CategoriesService;
import ua.restaurant.service.DishesService;
import ua.restaurant.utils.Constants;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;
    @Autowired
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryDTO>> getAllDishes() {
        log.info(Constants.GET_ALL_CATEGORIES);
        return ResponseEntity.ok(categoriesService.findAllCategories());
    }

}
