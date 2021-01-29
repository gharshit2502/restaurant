package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.restaurant.entity.Baskets;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Logins;

import java.util.List;

public interface BasketRepository  extends JpaRepository<Baskets, Long> {
    public List<Baskets> findAllByLogin_LoginContains(String username);

}
