package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.dto.ItemDTO;
import ua.restaurant.entity.Orders;
import ua.restaurant.service.OrdersService;
import ua.restaurant.utils.Constants;
import ua.restaurant.utils.ContextHelpers;

import javax.validation.Valid;
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
    public ResponseEntity<List<Orders>> getOrders() {
        log.info(Constants.GET_ALL_ORDER_USER + ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(ordersService.findAllUserOrders());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Orders>> getAllOrders() {
        log.info(Constants.GET_ALL_ORDER_MANAGER + ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(ordersService.findAllOrders());
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> add () {
        log.info(Constants.ADD_NEW_ORDER + ContextHelpers.getAuthorizedLogin().getLogin());
        try {
            return ResponseEntity.ok(ordersService.saveNewItem());
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/confirm")
    public boolean confirm (@Valid @RequestBody ItemDTO item) {
        log.info(Constants.CONFIRM + item.toString());
        try {
            ordersService.confirm(item);
            return true;
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/payment")
    public boolean payment (@Valid @RequestBody ItemDTO item) {
        log.info(Constants.PAYMENT + item.getItemId());
        try {
            ordersService.payment(item.getItemId());
            return true;
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
