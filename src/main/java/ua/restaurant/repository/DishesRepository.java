package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Dishes;

import java.util.List;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
//    Optional<Dish> findByName(String name);
    List<Dishes> findAllByOrderByIdDesc();
    List<Dishes> findAllByOrderByPriceAscIdAsc();
}
