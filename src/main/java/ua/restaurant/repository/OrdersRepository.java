package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.restaurant.entity.Orders;
import ua.restaurant.entity.Status;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByLogin_Id(Long id);

    @Modifying
    @Query("UPDATE Orders o SET o.status = :status WHERE o.id = :id AND o.login.id = :loginId")
    void updateStatus(@Param(value = "id") Long id,
                      @Param(value = "loginId") Long loginId,
                      @Param(value = "status") Status status);
}
