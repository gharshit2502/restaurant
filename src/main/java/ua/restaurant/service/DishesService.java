package ua.restaurant.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.config.Bundler;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Categories;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.CategoriesRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

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
                .dishes(Mapper.dishesToDishesDTO(page.getContent()))
                .categories(Mapper.categoriesToCategoriesDTO(categories))
                .currentPage(pageNo)
                .totalPages(page.getTotalPages())
                .sortField(sortField)
                .sortDirection(sortDirection)
                .categoryId(categoryId)
                .build();
    }

    public List<DishDTO> findAllDishes() {
        return Mapper.dishesToDishesDTO(dishesRepository.findAll());
    }

    public Dishes findById(Long id) {
        return dishesRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(Constants.DISH_NOT_FOUND));
    }

    @Transactional
    public Dishes saveNewDish (@NonNull Dishes dish) throws NoSuchElementException {
//        try {
        dish.setTime(LocalDateTime.now());
        dish.setCategories(categoriesRepository.findById(dish.getCategories().getId())
                .orElseThrow(() -> new NoSuchElementException(Constants.DISH_NOT_FOUND)));
        return dishesRepository.save(dish);
//        } catch (Exception e) {
//            throw new NoSuchElementException(Constants.LOGIN_EXISTS + loginDTO.getLogin());
//        }
    }

    public void update(@NonNull Dishes dish) {
        dish.setTime(LocalDateTime.now());
        dishesRepository.save(dish);
    }

    public void delete(@NonNull Long id) {
        dishesRepository.deleteById(id);
    }

}
