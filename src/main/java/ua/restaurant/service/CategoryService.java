package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.CategoriesDTO;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> findAll() {
        CategoriesDTO categories = new CategoriesDTO(categoryRepository.findAll());
        return categories.getCategories();
    }

}
