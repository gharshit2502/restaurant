package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.OrderItemDTO;
import ua.restaurant.entity.Orders;
import ua.restaurant.service.OrdersService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<Orders> getOrders() {
        return ordersService.findAllUserOrders();
    }

    @GetMapping("/getAll")
    public List<Orders> getAllOrders() {
        return ordersService.findAllOrders();
    }

    @PostMapping("/create")
    public Orders add (HttpServletRequest request) {
        try {
            log.info("Adding new order for user: " + request.getUserPrincipal().getName());
            return ordersService.saveNewItem();
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/confirm")
    public boolean confirm (@RequestBody OrderItemDTO item) {
        try {
            log.info("Confirm action for order: " + item.toString());
            ordersService.confirm(item);
            return true;
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/payment")
    public boolean payment (HttpServletRequest request) {
        try {
            log.info("Pay for order from: " + request.getUserPrincipal().getName());
            ordersService.payment();
            return true;
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
