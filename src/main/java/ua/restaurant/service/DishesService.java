package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.repository.DishesRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class DishesService {
    private final DishesRepository dishesRepository;
//    private final CategoriesService categoriesService;

    @Autowired
    public DishesService(DishesRepository dishesRepository, CategoriesService categoriesService) {
        this.dishesRepository = dishesRepository;
//        this.categoriesService = categoriesService;
    }

    public List<DishDTO> findAllDishes() {
        return dishesRepository.findAll().stream()
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(LocaleContextHolder.getLocale().equals(Locale.ENGLISH) ? d.getName_en() : d.getName_ua())
                        .category(LocaleContextHolder.getLocale().equals(Locale.ENGLISH)
                                ? d.getCategories().getCategory_en() : d.getCategories().getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }

}
