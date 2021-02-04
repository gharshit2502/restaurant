package ua.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Dishes;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
    Page<Dishes> findByCategories_Id(Long categoryId, Pageable pageable);
}
