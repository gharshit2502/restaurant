package ua.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.dto.DishDTO;
import ua.restaurant.dto.ItemDTO;
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

    /**
     * Create order from users basket items,
     * clean basket
     * @return saved order
     * @throws NoSuchElementException
     *          if basket is empty
     *          if cannot delete items
     *          if cannot save
     */
    @Transactional
    public Orders saveNewItem () throws NoSuchElementException {
        Logins user = ContextHelpers.getAuthorizedLogin();

        List<Baskets> basketsItems = basketRepository.findAllByLogin_Id(user.getId());
        if (basketsItems.isEmpty()) {
            log.info("empty");
            throw new NoSuchElementException(Constants.EMPTY_LIST);
        }

        List<DishDTO> dishes = Converter.basketToDishesDTO(basketsItems);

        BigDecimal total = Converter.getTotalPrice(dishes);
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

    }

    /**
     * Manager confirm next order stage
     * @param item given order
     */
    @Transactional
    public void confirm(ItemDTO item) {

        // TODO make UPDATE query
        // TODO exception msg

        Orders order = ordersRepository.findById(item.getItemId())
                .orElseThrow(() -> new NoSuchElementException(
                        Constants.ORDER_NOT_FOUND + item.getItemId()));

        if (!order.getStatus().equals(Status.DONE) &&
                !order.getStatus().equals(Status.NEW)) {
            order.setStatus(order.getStatus().next());
            ordersRepository.save(order);
        }
    }

    /**
     * update order status if user payed for it.
     * @param id order id
     */
    @Transactional
    public void payment(Long id) {
        try {
            Long loginId = ContextHelpers.getAuthorizedLogin().getId();
            ordersRepository.updateStatus(id, loginId, Status.PAYED);
        } catch (Exception e) {
            throw new NoSuchElementException(Constants.ORDER_PAYED + id);
        }
    }
}
