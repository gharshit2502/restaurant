package ua.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.restaurant.config.Bundler;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.Converter;

@Service
public class DishesService {
    @Value( "${page.size}" )
    private int pageSize;
    @Value( "${page.sortDefault}" )
    private String sortDefault;
    @Value( "${page.sortDirectionDefault}" )
    private String sortDirectionDefault;

    private final DishesRepository dishesRepository;
    private final Bundler bundler;
    @Autowired
    public DishesService(DishesRepository dishesRepository,
                         Bundler bundler) {
        this.dishesRepository = dishesRepository;
        this.bundler = bundler;
    }

    /**
     * Gets paginated dto for main page
     * @param pageNo page number
     * @param sortField field for sort
     * @param sortDirection asc or desc sort
     * @param categoryId if there is filter by categories // TODO filter by category
     * @return dto for front
     */
    public PageableDishesDTO findAllDishesPaginated(
            Integer pageNo, String sortField, String sortDirection, Integer categoryId) {
        sortField = sortField == null ? sortDefault :
                (sortField.equals("name")) ? bundler.getMsg("db.name") : sortField;
        sortDirection = sortDirection == null ? sortDirectionDefault : sortDirection;

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        Page<Dishes> page = dishesRepository.findAll(pageable);

        return PageableDishesDTO.builder()
                .dishes(Converter.dishesToDishesDTO(page.getContent()))
                .currentPage(pageNo)
                .totalPages(page.getTotalPages())
                .sortField(sortField)
                .sortDirection(sortDirection)
                .build();
    }

}
