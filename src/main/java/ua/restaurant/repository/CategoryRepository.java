package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Category;
import ua.restaurant.entity.Dish;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Optional<Dish> findByName(String name);
}
