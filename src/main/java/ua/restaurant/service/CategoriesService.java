package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Categories;
import ua.restaurant.repository.CategoriesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static ua.restaurant.config.Constants.LANGUAGE_EN;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoryDTO> findAll() {
        return categoriesRepository.findAll().stream()
                .map(c -> CategoryDTO.builder()
                        .id(c.getId())
                        .category(LocaleContextHolder.getLocale().equals(Locale.ENGLISH)
                                ? c.getCategory_en() : c.getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }

}
