package ua.restaurant.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.config.Bundler;
import ua.restaurant.dto.BasketDTO;
import ua.restaurant.utils.Constants;
import ua.restaurant.dto.ItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;
import ua.restaurant.repository.BasketRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.utils.ContextHelpers;
import ua.restaurant.utils.Mapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketsService {
    private final BasketRepository basketRepository;
    private final DishesRepository dishesRepository;
    private final Bundler bundler;
    @Autowired
    public BasketsService(BasketRepository basketRepository,
                          DishesRepository dishesRepository,
                          Bundler bundler) {
        this.basketRepository = basketRepository;
        this.dishesRepository = dishesRepository;
        this.bundler = bundler;
    }

    /**
     * Get all dishes, that are in authorized user basket
     * @return list of baskets
     */
    public BasketDTO findAllDishes() {
        List<DishDTO> dishes = Mapper.basketToDishesDTO(
                basketRepository.findAllByLogin_Id(
                        ContextHelpers.getAuthorizedLogin().getId()));
        return BasketDTO.builder()
                .dishes(dishes)
                .totalPrice(Mapper.getTotalPrice(dishes))
                .build();
    }

    /**
     * Save new dish to users basket
     * @param itemDTO dto request from user
     * @return saved basket entity
     * @throws NoSuchElementException
     *      - if dish not found
     *      - if cannot save
     */
    @Transactional
    public Baskets saveNewItem (@NonNull ItemDTO itemDTO) {
        Logins user = ContextHelpers.getAuthorizedLogin();

        Dishes dish = dishesRepository.findById(itemDTO.getItemId())
                .orElseThrow(() -> new NoSuchElementException(
                        bundler.getLogMsg(Constants.BASKET_CREATE_DBE) + itemDTO.getItemId()));

        return basketRepository.save(Baskets.builder()
                .login(user)
                .dishes(dish)
                .build());
    }

    /**
     * delete one item from users basket
     * (there can be more than one identical dishes)
     * @param id dish id
     */
    @Transactional
    public void delete(@NonNull Long id) {
        List<Baskets> list = basketRepository.findBasketsByDishes_Id(id);
        if (list.isEmpty()) {
            throw new NoSuchElementException(bundler.getLogMsg(Constants.BASKET_DELETE_DBE));
        }
        basketRepository.delete(list.get(0));
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
