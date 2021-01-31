package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.Baskets;

import java.util.List;

public interface BasketRepository  extends JpaRepository<Baskets, Long> {
    List<Baskets> findAllByLogin_LoginContains(String username);
    void deleteBasketsByLoginLoginContains(String username);

}
