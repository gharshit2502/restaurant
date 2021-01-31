package ua.restaurant.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.service.BasketsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

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
    public List<DishDTO> getDishes(HttpServletRequest request) {
        return basketsService.findAllDishes(request.getUserPrincipal().getName());
    }

    @PostMapping("/create")
    public Baskets add (@RequestBody BasketItemDTO basketItemDTO, HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("Add new item to basket: " + basketItemDTO.toString());
            return basketsService.saveNewItem(basketItemDTO, request.getUserPrincipal().getName());
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot add");
            return null;
        }
    }

    @PostMapping("/deleteAll")
    public boolean delete (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("delete all items from basket for user: " + request.getUserPrincipal().getName());
            basketsService.deleteByLogin(request.getUserPrincipal().getName());
            return true;
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot delete");
            return false;
        }
    }

}
