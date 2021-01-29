package ua.restaurant.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.repository.BasketRepository;
import ua.restaurant.repository.DishesRepository;
import ua.restaurant.repository.LoginsRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BasketsService {

    private final BasketRepository basketRepository;
    private final DishesRepository dishesRepository;

    @Autowired
    private LoginsRepository loginsRepository;

    @Autowired
    public BasketsService(BasketRepository basketRepository, DishesRepository dishesRepository) {
        this.basketRepository = basketRepository;
        this.dishesRepository = dishesRepository;
    }

    public List<DishDTO> findAllDishes(String username) {
        return basketRepository.findAllByLogin_LoginContains(username).stream()
                .map(Baskets::getDishes)
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(LocaleContextHolder.getLocale().equals(Locale.ENGLISH) ? d.getName_en() : d.getName_ua())
                        .category(LocaleContextHolder.getLocale().equals(Locale.ENGLISH)
                                ? d.getCategories().getCategory_en() : d.getCategories().getCategory_ua())
                        .build())
                .collect(Collectors.toList());
    }

    public List<DishDTO> findAllDishes() {
        return basketRepository.findAll().stream()
                .map(Baskets::getDishes)
                .map(d -> DishDTO.builder()
                        .id(d.getId())
                        .price(d.getPrice())
                        .name(LocaleContextHolder.getLocale().equals(Locale.ENGLISH) ? d.getName_en() : d.getName_ua())
                        .category(LocaleContextHolder.getLocale().equals(Locale.ENGLISH)
                                ? d.getCategories().getCategory_en() : d.getCategories().getCategory_ua())
                        .build())
                .collect(Collectors.toList());
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

//    public void delete(@NonNull BasketItemDTO basketItemDTO) {
//        dishesRepository.deleteById(basketItemDTO.getDishId());

    public void deleteByLogin(@NonNull String username) {
        basketRepository.deleteBasketsByLoginLoginContains(username);
    }

}
