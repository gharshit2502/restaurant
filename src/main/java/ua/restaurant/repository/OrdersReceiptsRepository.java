package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.restaurant.entity.OrdersReceipts;

public interface OrdersReceiptsRepository extends JpaRepository<OrdersReceipts, Long> {
}
