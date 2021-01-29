package ua.restaurant.controller;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.service.DishesService;

import java.util.Arrays;
import java.util.List;

//@Slf4j
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
    public List<DishDTO> getMain() {
//        log.info("{}", loginService.getAllUsers());
//        LOGGER.trace(LocaleContextHolder.getLocale().toString());

//        test(LOGGER);
//
//        LOGGER.trace("A TRACE Message");
//        LOGGER.debug("A DEBUG Message");
//        LOGGER.info("An INFO Message");
//        LOGGER.warn("A WARN Message");
//        LOGGER.error("An ERROR Message");

        return dishesService.findAllDishes();
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
