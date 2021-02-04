package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.service.DishesService;
import ua.restaurant.utils.Constants;

import javax.validation.Valid;

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
    findPaginated(@Valid @PathVariable (value = "pageNo") Integer pageNo,
                  @RequestParam (value = "sortField", required = false) String sortField,
                  @RequestParam (value = "sortDirection", required = false) String sortDirection,
                  @RequestParam (value = "categoryId", required = false) Long categoryId)  {

        log.error(pageNo.toString() + sortField + sortDirection + categoryId.toString());

        log.info(Constants.GET_PAGE_DISHES + pageNo);
        try {
            return ResponseEntity.ok(
                    dishesService.findAllDishesPaginated(
                            pageNo, sortField, sortDirection, categoryId));
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
