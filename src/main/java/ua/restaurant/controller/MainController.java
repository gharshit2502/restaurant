package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.service.DishesService;

@Slf4j
@RestController
@RequestMapping(value = "api/")
public class MainController {
    private final DishesService dishesService;

    @Autowired
    public MainController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("/get/{pageNo}")
    public ResponseEntity<PageableDishesDTO>
    findPaginated(@PathVariable (value = "pageNo") int pageNo,
                  @RequestParam (value = "sortField", required = false) String sortField,
                  @RequestParam (value = "sortDirection", required = false) String sortDirection)  {
        try {
            log.info("Get page of dishes #" + pageNo);
            return ResponseEntity.ok(dishesService.findAllDishesPaginated(pageNo, sortField, sortDirection));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
