package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.config.Bundler;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.CategoriesRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.Converter;

import java.util.List;

@Service
public class DishesService {
    @Value( "${page.size}" )
    private int pageSize;
    @Value( "${page.sortDefault}" )
    private String sortDefault;
    @Value( "${page.sortDirectionDefault}" )
    private String sortDirectionDefault;

    private final DishesRepository dishesRepository;
    private final CategoriesRepository categoriesRepository;
    private final Bundler bundler;
    @Autowired
    public DishesService(DishesRepository dishesRepository,
                         CategoriesRepository categoriesRepository,
                         Bundler bundler) {
        this.dishesRepository = dishesRepository;
        this.categoriesRepository = categoriesRepository;
        this.bundler = bundler;
    }

    /**
     * Gets paginated dto for main page
     * @param pageNo page number
     * @param sortField field for sort
     * @param sortDirection asc or desc sort
     * @param categoryId if there is filter by categories
     * @return dto for front
     */
    @Transactional
    public PageableDishesDTO findAllDishesPaginated(
            Integer pageNo, String sortField, String sortDirection, Long categoryId) {
        sortField = sortField.equals(Constants.NULL)
                ? sortDefault
                : (sortField.equals(Constants.NAME)) ? bundler.getMsg("db.name") : sortField;

        sortDirection = sortDirection.equals(Constants.NULL)
                ? sortDirectionDefault
                : sortDirection;

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Dishes> page = (categoryId == null || categoryId == 0)
                ? dishesRepository.findAll(pageable)
                : dishesRepository.findByCategories_Id(categoryId, pageable);

        List<Categories> categories = categoriesRepository.findAll();

        return PageableDishesDTO.builder()
                .dishes(Converter.dishesToDishesDTO(page.getContent()))
                .categories(Converter.categoriesToCategoriesDTO(categories))
                .currentPage(pageNo)
                .totalPages(page.getTotalPages())
                .sortField(sortField)
                .sortDirection(sortDirection)
                .categoryId(categoryId)
                .build();
    }

}
