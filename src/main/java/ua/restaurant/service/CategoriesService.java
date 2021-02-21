package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.CategoryDTO;
import ua.restaurant.repository.CategoriesRepository;
import ua.restaurant.utils.Mapper;

import java.util.List;

@Service
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    /**
     * Get categories for creating dish
     * @return List<CategoryDTO>
     */
    public List<CategoryDTO> findAllCategories() {
        return Mapper.categoriesToCategoriesDTO(categoriesRepository.findAll());
    }


}
