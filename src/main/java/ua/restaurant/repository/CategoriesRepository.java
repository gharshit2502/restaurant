package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
