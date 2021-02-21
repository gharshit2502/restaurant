package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.ItemDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.service.DishesService;
import ua.restaurant.utils.Constants;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/manager/dishes")
public class DishesController {
    private final DishesService dishesService;
    @Autowired
    public DishesController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        log.info(Constants.GET_ALL_DISHES);
        return ResponseEntity.ok(dishesService.findAllDishes());
    }

    @PostMapping("/create")
    public ResponseEntity<Dishes> create (@Valid @RequestBody Dishes dish) {
        log.info(Constants.CREATE_NEW_DISH);
        try {
            return ResponseEntity.ok(dishesService.saveNewDish(dish));
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/update")
    public ResponseEntity<Dishes> getDish(@Valid @RequestParam (value = "id") Long id) {
        log.info(Constants.GET_DISH + id);
        try {
            return ResponseEntity.ok(dishesService.findById(id));
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update")
    public boolean update (@Valid @RequestBody Dishes dish) {
        log.info(Constants.UPDATE_DISH + dish.getId());
        try {
            dishesService.update(dish);
            return true;
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public boolean delete (@Valid @RequestBody ItemDTO itemDTO) {
        log.info(Constants.DELETE_DISH + itemDTO.getItemId());
        try {
            dishesService.delete(itemDTO.getItemId());
            return true;
        } catch (Exception e) {
            log.warn(Constants.ERROR_DELETE);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.ERROR_DELETE);
        }
    }
}
