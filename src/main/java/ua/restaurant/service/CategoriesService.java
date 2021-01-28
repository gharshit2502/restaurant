package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.CategoriesDTO;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.repository.CategoriesRepository;

import java.util.List;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<CategoryDTO> findAll() {
        CategoriesDTO categories = new CategoriesDTO(categoriesRepository.findAll());
        return categories.getCategories();
    }

}
