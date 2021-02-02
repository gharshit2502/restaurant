package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Dishes;

import java.util.List;
import java.util.Optional;

public interface DishesRepository extends JpaRepository<Dishes, Long> {
}
