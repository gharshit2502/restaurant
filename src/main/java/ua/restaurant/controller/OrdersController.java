package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.restaurant.config.Bundler;
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
    private final Bundler bundler;
    @Autowired
    public OrdersController(OrdersService ordersService,
                            Bundler bundler) {
        this.ordersService = ordersService;
        this.bundler = bundler;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Orders>> getOrders() {
        log.info(bundler.getLogMsg(Constants.ORDERS_ALL) +
                ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(ordersService.findAllUserOrders());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Orders>> getAllOrders() {
        log.info(bundler.getLogMsg(Constants.ORDERS_ALL_MANAGER) +
                ContextHelpers.getAuthorizedLogin().getLogin());
        return ResponseEntity.ok(ordersService.findAllOrders());
    }

    @PostMapping("/create")
    public ResponseEntity<Orders> create () {
        log.info(bundler.getLogMsg(Constants.ORDERS_CREATE) +
                ContextHelpers.getAuthorizedLogin().getLogin());
        try {
            return ResponseEntity.ok(ordersService.saveNewItem());
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/confirm")
    public void confirm (@Valid @RequestBody ItemDTO item) {
        log.info(bundler.getLogMsg(Constants.ORDERS_UPDATE) + item.getItemId());
        try {
            ordersService.confirm(item.getItemId());
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/payment")
    public void payment (@Valid @RequestBody ItemDTO item) {
        log.info(bundler.getLogMsg(Constants.ORDERS_UPDATE) + item.getItemId());
        try {
            ordersService.payment(item.getItemId());
        } catch (Exception e){
            log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
