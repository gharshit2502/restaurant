package ua.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/")
    public String mainPage(){
        return "main";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/signup")
    public String registrationPage(){
        return "signup";
    }

    @RequestMapping("/basket")
    public String basketPage(){
        return "basket";
    }

    @RequestMapping("/orders")
    public String ordersPage(){
        return "orders";
    }

    @RequestMapping("/payment")
    public String paymentPage(){
        return "payment";
    }

    @RequestMapping("manager/orders")
    public String ordersManagerPage(){
        return "orders_manager";
    }

}
