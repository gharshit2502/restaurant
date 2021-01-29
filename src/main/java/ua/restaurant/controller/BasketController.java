package ua.restaurant.controller;

import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.service.BasketsService;
import ua.restaurant.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/basket")
public class BasketController {
    private final BasketsService basketsService;
    private static final Logger log = LogManager.getLogger(LoginController.class);

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
            log.info("add new item to basket: " + basketItemDTO.toString());
            return basketsService.saveNewItem(basketItemDTO, request.getUserPrincipal().getName());
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.info("Cannot add");
        }
        return null;
    }

    @PostMapping("/deleteAll")
    public boolean delete (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("delete all items from basket for user: " + request.getUserPrincipal().getName());
            basketsService.deleteByLogin(request.getUserPrincipal().getName());
            return true;
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.info("Cannot delete");
        }
        return false;
    }

}
