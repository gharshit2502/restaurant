package ua.restaurant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.dto.LoginsDTO;
import ua.restaurant.service.DishesService;

import java.util.Locale;


@RestController
@RequestMapping(value = "/api")
public class MainController {
    private final DishesService dishesService;
    @Autowired
    public MainController(DishesService dishesService) {
        this.dishesService = dishesService;
    }


    @GetMapping("/get")
    public DishesDTO getMain() {
//        log.info("{}", loginService.getAllUsers());

        System.out.println(LocaleContextHolder.getLocale());


        return dishesService.getAllDishes();
    }

}
