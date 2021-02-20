package ua.restaurant.repository;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Dishes;

import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
    Page<Dishes> findByCategories_Id(Long categoryId, Pageable pageable);
    Optional<Dishes> findById(@NonNull Long id);
    void deleteById(Long id);
}
