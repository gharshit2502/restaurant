package ua.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.Converter;

import java.util.List;

@Slf4j
@Service
public class DishesService {
    @Value( "${page.size}" )
    private int pageSize;

    private final DishesRepository dishesRepository;

    @Autowired
    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public List<DishDTO> findAllDishes() {
        return Converter.dishesToDishesDTO(dishesRepository.findAll());
    }

    public List<DishDTO> findAllDishesPaginated(int pageNo) {
        Page<Dishes> page = findPaginated(pageNo);

        log.info(page.getContent().toString());
        log.info(String.valueOf(page.getTotalPages()));
        log.info(String.valueOf(page.getTotalElements()));

        return Converter.dishesToDishesDTO(page.getContent());
    }

    public Page<Dishes> findPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.dishesRepository.findAll(pageable);
    }

}
