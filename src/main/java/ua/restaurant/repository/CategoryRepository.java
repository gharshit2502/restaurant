package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
//    Optional<Dish> findByName(String name);
}
