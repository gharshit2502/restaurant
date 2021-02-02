package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.service.BasketsService;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.ContextHelpers;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/basket")
public class BasketController {
    private final BasketsService basketsService;

    @Autowired
    public BasketController(BasketsService basketsService) {
        this.basketsService = basketsService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<DishDTO>> getDishes() {
        return ResponseEntity.ok(basketsService.findAllDishes());
    }

    @PostMapping("/create")
    public ResponseEntity<Baskets> add (@RequestBody BasketItemDTO basketItemDTO) {
        try {
            log.info("Add new item to basket: " + basketItemDTO.toString());
            return ResponseEntity.ok(
                    basketsService.saveNewItem(basketItemDTO));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // TODO delete one

    @DeleteMapping("/deleteAll")
    public boolean delete () {
        try {
            log.info("delete all items from basket for user: " + ContextHelpers.getAuthorizedLogin().getLogin());
            basketsService.deleteByLogin(ContextHelpers.getAuthorizedLogin().getId());
            return true;
        } catch (Exception e) {
            log.warn(Constants.ERROR_DELETE_ALL);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERROR_DELETE_ALL);

        }
    }

}
