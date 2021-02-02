package ua.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.OrderItemDTO;
import ua.restaurant.entity.*;
import ua.restaurant.repository.*;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.ContextHelpers;
import ua.restaurant.utils.Converter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final BasketRepository basketRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository,
                         BasketRepository basketRepository) {
        this.ordersRepository = ordersRepository;
        this.basketRepository = basketRepository;
    }

    /**
     * Gets all user orders
     * @return list of orders
     */
    public List<Orders> findAllUserOrders() {
        return ordersRepository.findOrdersByLogin_Id(
                ContextHelpers.getAuthorizedLogin().getId());
    }

    /**
     * Gets all orders for manager order page
     * @return list of orders
     */
    public List<Orders> findAllOrders() {
        return ordersRepository.findAll();
    }

    // TODO make query

    @Transactional
    public Orders saveNewItem () throws NoSuchElementException {
        try {
            Logins user = ContextHelpers.getAuthorizedLogin();

            List<Baskets> basketsItems = basketRepository.findAllByLogin_Id(user.getId());
            if (basketsItems.isEmpty()) {
                throw new NoSuchElementException(Constants.EMPTY_LIST);
            }

            List<DishDTO> dishes = Converter.basketToDishesDTO(basketsItems);

            BigDecimal total = dishes.stream()
                    .map(DishDTO::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            log.info(total.toString());

            basketRepository.deleteByLogin_Id(user.getId());
            log.info(Constants.DELETE_ALL_BASKET);

            // TODO create orderList

            return ordersRepository.save(Orders.builder()
                    .login(user)
                    .totalPrice(total)
                    .status(Status.NEW)
                    .time(LocalDateTime.now())
                    .build());
        } catch (Exception e) {
            log.info("Cannot create order");
            throw new NoSuchElementException();
        }
    }

    @Transactional
    public void confirm(OrderItemDTO item) {

        // TODO make query

        Orders order = ordersRepository.findById(item.getOrderId())
                .orElseThrow(() -> new NoSuchElementException(
                        Constants.ORDER_NOT_FOUND + item.getOrderId()));

        if (!order.getStatus().equals(Status.DONE) &&
                !order.getStatus().equals(Status.NEW)) {
            order.setStatus(order.getStatus().next());
            ordersRepository.save(order);
        }
    }

    @Transactional
    public void payment() {

        // TODO make query, Make pay by order id

        Orders order = ordersRepository
                .findTopByLogin_IdOrderByTimeDesc(ContextHelpers.getAuthorizedLogin().getId())
                .orElseThrow(() -> new NoSuchElementException(
                        Constants.ORDER_NOT_FOUND));

        if (order.getStatus().equals(Status.NEW)) {
            ordersRepository.updateStatus(order.getId(), Status.PAYED);
        }
    }
}
