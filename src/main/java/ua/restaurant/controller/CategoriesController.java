package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.config.Bundler;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.service.CategoriesService;
import ua.restaurant.utils.Constants;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final Bundler bundler;
    @Autowired
    public CategoriesController(CategoriesService categoriesService,
                                Bundler bundler) {
        this.categoriesService = categoriesService;
        this.bundler = bundler;
    }

    /**
     * Get categories for creating dish
     * @return List<CategoryDTO>
     */
    @GetMapping("/get")
    public ResponseEntity<List<CategoryDTO>> getAllDishes() {
        log.info(bundler.getLogMsg(Constants.CATEGORIES_ALL));
        return ResponseEntity.ok(categoriesService.findAllCategories());
    }

}
