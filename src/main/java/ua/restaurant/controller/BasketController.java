package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.BasketDTO;
import ua.restaurant.dto.ItemDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.service.BasketsService;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.ContextHelpers;

import javax.validation.Valid;

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
    public ResponseEntity<BasketDTO> getDishes() {
        log.info(Constants.GET_ALL_BASKET_DISHES + ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(basketsService.findAllDishes());
    }

    @PostMapping("/create")
    public ResponseEntity<Baskets> add (@Valid @RequestBody ItemDTO itemDTO) {
        log.info(Constants.ADD_NEW_DISH + itemDTO.toString());
        try {
            return ResponseEntity.ok(
                    basketsService.saveNewItem(itemDTO));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public boolean delete (@Valid @RequestBody ItemDTO itemDTO) {
        log.info(Constants.DELETE_ONE + itemDTO.getItemId());
        try {
            basketsService.delete(itemDTO.getItemId());
            return true;
        } catch (Exception e) {
            log.warn(Constants.ERROR_DELETE);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERROR_DELETE);
        }
    }

    @DeleteMapping("/deleteAll")
    public boolean deleteAll () {
        log.info(Constants.DELETE_ALL + ContextHelpers.getAuthorizedLogin().getLogin());
        try {
            basketsService.deleteByLogin(ContextHelpers.getAuthorizedLogin().getId());
            return true;
        } catch (Exception e) {
            log.warn(Constants.ERROR_DELETE_ALL);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERROR_DELETE_ALL);

        }
    }

}
