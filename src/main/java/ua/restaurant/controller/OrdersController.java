package ua.restaurant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.dto.BasketItemDTO;
import ua.restaurant.dto.OrderItemDTO;
import ua.restaurant.entity.Orders;
import ua.restaurant.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/orders")
public class OrdersController {
    private final OrdersService ordersService;
    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/get")
    public List<Orders> getOrders(HttpServletRequest request) {
        return ordersService.findAllOrders(request.getUserPrincipal().getName());
    }

    @GetMapping("/getAll")
    public List<Orders> getAllOrders(HttpServletRequest request) {
        return ordersService.findAllOrders();
    }

    @PostMapping("/create")
    public Orders add (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("adding new order to user: " + request.getUserPrincipal().getName());
            return ordersService.saveNewItem(request.getUserPrincipal().getName());
        } catch (Exception e){
            log.info("Cannot add");
            response.sendError(HttpStatus.BAD_REQUEST.value());
        }
        return null;
    }

    @PostMapping("/confirm")
    public boolean confirm (@RequestBody OrderItemDTO item, HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("confirm action for order: " + item.toString());
            ordersService.confirm(item);
            return true;
        } catch (Exception e){
            log.info("Cannot confirm");
            response.sendError(HttpStatus.BAD_REQUEST.value());
        }
        return false;
    }

    @PostMapping("/payment")
    public boolean payment (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("pay for order from: " + request.getUserPrincipal().getName());
            ordersService.payment(request.getUserPrincipal().getName());
            return true;
        } catch (Exception e){
            log.info("Cannot get payment");
            response.sendError(HttpStatus.BAD_REQUEST.value());
        }
        return false;
    }
}
