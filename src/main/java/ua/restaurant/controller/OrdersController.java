package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.dto.OrderItemDTO;
import ua.restaurant.entity.Orders;
import ua.restaurant.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping(value = "/api/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/get")
    public List<Orders> getOrders(HttpServletRequest request) {
        return ordersService.findAllOrders(request.getUserPrincipal().getName());
    }

    @GetMapping("/getAll")
    public List<Orders> getAllOrders() {
        return ordersService.findAllOrders();
    }

    @PostMapping("/create")
    public Orders add (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("Adding new order to user: " + request.getUserPrincipal().getName());
            return ordersService.saveNewItem(request.getUserPrincipal().getName());
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot add");
            return null;
        }
    }

    @PostMapping("/confirm")
    public boolean confirm (@RequestBody OrderItemDTO item, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("Confirm action for order: " + item.toString());
            ordersService.confirm(item);
            return true;
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot confirm");
            return false;
        }
    }

    @PostMapping("/payment")
    public boolean payment (HttpServletRequest request, HttpServletResponse response) throws NoSuchElementException, IOException {
        try {
            log.info("Pay for order from: " + request.getUserPrincipal().getName());
            ordersService.payment(request.getUserPrincipal().getName());
            return true;
        } catch (Exception e){
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot get payment");
            return false;
        }
    }
}
