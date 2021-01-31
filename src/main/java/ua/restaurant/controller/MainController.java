package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.service.DishesService;

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

    @GetMapping("/get")
    public List<DishDTO> getMain() {
        return dishesService.findAllDishes();
    }

    @GetMapping("/get/{pageNo}")
    public List<DishDTO> findPaginated(@PathVariable (value = "pageNo") int pageNo) {
        return dishesService.findAllDishesPaginated(pageNo);
    }

//    @GetMapping("/get")
//    public Page<Dishes> getMain(
//            @PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable){
//        return dishesService.findAllDishes(pageable);
//    }

}
