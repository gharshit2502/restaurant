package ua.restaurant.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;
import ua.restaurant.repository.BasketRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.repository.LoginsRepository;
import ua.restaurant.utils.Converter;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class BasketsService {

    private final BasketRepository basketRepository;
    private final DishesRepository dishesRepository;
    private final LoginsRepository loginsRepository;

    @Autowired
    public BasketsService(BasketRepository basketRepository,
                          DishesRepository dishesRepository,
                          LoginsRepository loginsRepository) {
        this.basketRepository = basketRepository;
        this.dishesRepository = dishesRepository;
        this.loginsRepository = loginsRepository;
    }

    public List<DishDTO> findAllDishes(String username) {
        return Converter.basketToDishesDTO(
                basketRepository.findAllByLogin_LoginContains(username));
    }

    @Transactional
    public Baskets saveNewItem (@NonNull BasketItemDTO basketItemDTO, String username) throws NoSuchElementException {
        try {
            // TODO check wrong dish id
            Logins user = loginsRepository.findByLogin(username)
                    .orElseThrow(NoSuchElementException::new);
            log.info(user.toString());

            Dishes dish = dishesRepository.findById(basketItemDTO.getDishId())
                    .orElseThrow(NoSuchElementException::new);
            log.info(dish.toString());

            return basketRepository.save(Baskets.builder()
                    .login(user)
                    .dishes(dish)
                    .build());
        } catch (Exception e) {
            log.info("Dish do not exist, id: " + basketItemDTO.getDishId());
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void deleteByLogin(@NonNull String username) {
        basketRepository.deleteByLogin_Login(username);
    }

}
