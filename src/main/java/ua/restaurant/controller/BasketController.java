package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.config.Bundler;
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
    private final Bundler bundler;
    @Autowired
    public BasketController(BasketsService basketsService,
                            Bundler bundler) {
        this.basketsService = basketsService;
        this.bundler = bundler;
    }

    /**
     * Get all baskets dishes for customer
     * @return BasketDTO
     */
    @GetMapping("/get")
    public ResponseEntity<BasketDTO> getDishes() {
        log.info(bundler.getLogMsg(Constants.BASKET_ALL) +
                ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(basketsService.findAllDishes());
    }

    /**
     * Add dish to basket
     * @param itemDTO dish id
     * @return Baskets
     */
    @PostMapping("/create")
    public ResponseEntity<Baskets> create(@Valid @RequestBody ItemDTO itemDTO) {
        log.info(bundler.getLogMsg(Constants.BASKET_CREATE) + itemDTO.getItemId());
        try {
            return ResponseEntity.ok(basketsService.saveNewItem(itemDTO));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Delete from basket one item
     * @param itemDTO dish id
     */
    @DeleteMapping("/delete")
    public void delete (@Valid @RequestBody ItemDTO itemDTO) {
        log.info(bundler.getLogMsg(Constants.BASKET_DELETE) + itemDTO.getItemId());
        try {
            basketsService.delete(itemDTO.getItemId());
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bundler.getLogMsg(Constants.BASKET_DELETE_DBE));
        }
    }

    /**
     * delete all basket list for customer
     */
    @DeleteMapping("/deleteAll")
    public void deleteAll () {
        Long userId = ContextHelpers.getAuthorizedLogin().getId();
        log.info(bundler.getLogMsg(Constants.BASKET_DELETE_ALL) + userId);
        try {
            basketsService.deleteByLogin(userId);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bundler.getLogMsg(Constants.BASKET_DELETE_ALL_DBE));

        }
    }

}
