package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Baskets;

import java.util.List;

public interface BasketRepository  extends JpaRepository<Baskets, Long> {
    List<Baskets> findAllByLogin_Id(Long id);
    List<Baskets> findBasketsByDishes_Id(Long id);
    void deleteByLogin_Id(Long id);

}
