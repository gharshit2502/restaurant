package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.PageableDishesDTO;
import ua.restaurant.entity.Dishes;
import ua.restaurant.service.DishesService;
import ua.restaurant.utils.Converter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
    public PageableDishesDTO findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                           @RequestParam (value = "sortField", required = false) String sortField,
                                           @RequestParam (value = "sortDirection", required = false) String sortDirection,
                                           HttpServletResponse response) throws IOException {
        try {
            log.info("Get page of dishes #" + pageNo);
            return dishesService.findAllDishesPaginated(pageNo, sortField, sortDirection);
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot get page");
            return null;
        }

    }

}
