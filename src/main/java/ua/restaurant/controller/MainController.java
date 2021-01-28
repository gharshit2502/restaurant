package ua.restaurant.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.restaurant.dto.DishesDTO;
import ua.restaurant.service.CategoriesService;
import ua.restaurant.service.DishesService;

import java.util.Arrays;

import static ua.restaurant.config.Constants.LANGUAGE_EN;


@RestController
@RequestMapping(value = "api/")
public class MainController {
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);
    private final DishesService dishesService;

    @Autowired
    public MainController(DishesService dishesService) {
        this.dishesService = dishesService;
    }

    @GetMapping("/get")
    public DishesDTO getMain() {
//        log.info("{}", loginService.getAllUsers());
//        LOGGER.trace(LocaleContextHolder.getLocale().toString());

        test(LOGGER);

        LOGGER.trace("A TRACE Message");
        LOGGER.debug("A DEBUG Message");
        LOGGER.info("An INFO Message");
        LOGGER.warn("A WARN Message");
        LOGGER.error("An ERROR Message");

        LocaleContextHolder.getLocale().toString().equals(LANGUAGE_EN);

        return dishesService.getAllDishes();
    }

    public static void test(Logger log) {
        System.out.println("Current log level " + log.getName() + " " + log.getLevel());
        System.out.println(log.getName());

        Level[] levels = Level.values();
        Arrays.sort(levels);
        for (Level l : levels) {
            log.log(l, l.toString() + " {}", l.intLevel());
        }
    }


}
