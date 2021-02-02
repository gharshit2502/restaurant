package ua.restaurant.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.utils.Constants;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;
import ua.restaurant.repository.BasketRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.ContextHelpers;
import ua.restaurant.utils.Converter;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BasketsService {

    private final BasketRepository basketRepository;
    private final DishesRepository dishesRepository;

    @Autowired
    public BasketsService(BasketRepository basketRepository,
                          DishesRepository dishesRepository) {
        this.basketRepository = basketRepository;
        this.dishesRepository = dishesRepository;
    }

    /**
     * Get all dishes, that are in authorized user basket
     * @return list of baskets
     */
    public List<DishDTO> findAllDishes() {
        return Converter.basketToDishesDTO(
                basketRepository.findAllByLogin_Id(ContextHelpers.getAuthorizedLogin().getId()));
    }

    /**
     * Save new dish to users basket
     * @param basketItemDTO dto request from user
     * @return saved basket entity
     * @throws NoSuchElementException
     *      - if dish not found
     *      - if cannot save
     */
    @Transactional
    public Baskets saveNewItem (@NonNull BasketItemDTO basketItemDTO) {
        Logins user = ContextHelpers.getAuthorizedLogin();

        // TODO maybe make custom query for one call to db

        Dishes dish = dishesRepository.findById(basketItemDTO.getDishId())
                .orElseThrow(() ->
                        new NoSuchElementException(Constants.DISH_NOT_FOUND + basketItemDTO.getDishId()));
        log.info(dish.toString());

        return basketRepository.save(Baskets.builder()
                .login(user)
                .dishes(dish)
                .build());
    }

    /**
     * delete all items from users basket
     * @param id authorized user id
     */
    @Transactional
    public void deleteByLogin(@NonNull Long id) {
        basketRepository.deleteByLogin_Id(id);
    }

}
