package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.ItemDTO;
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
    public ResponseEntity<Baskets> add (@RequestBody ItemDTO itemDTO) {
        try {
            log.info("Add new item to basket: " + itemDTO.toString());
            return ResponseEntity.ok(
                    basketsService.saveNewItem(itemDTO));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public boolean delete (@RequestBody ItemDTO itemDTO) {
        try {

            log.info("delete one item from basket for user: " + ContextHelpers.getAuthorizedLogin().getLogin());
            System.out.println(itemDTO.getItemId());
            basketsService.delete(itemDTO.getItemId());
            return true;
        } catch (Exception e) {
            log.warn(Constants.ERROR_DELETE);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERROR_DELETE);
        }
    }

    @DeleteMapping("/deleteAll")
    public boolean deleteAll () {
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
