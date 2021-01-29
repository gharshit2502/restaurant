package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.restaurant.entity.Dishes;
import ua.restaurant.entity.Orders;
import ua.restaurant.entity.Status;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    public List<Orders> findOrdersByLogin_Login(String username);
    public Optional<Orders> findTopByLogin_LoginOrderByTimeDesc(String username);

    @Modifying
    @Query("UPDATE Orders o set o.status = :status where o.id = :id")
    void updateStatus(@Param(value = "id") long id, @Param(value = "status") Status status);
}
