package ua.restaurant.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @RequestMapping("/")
    public String mainPage(@RequestParam(value = "sort", required = false) String sort, Model model){
//        model.addAttribute(sort, sort != null);
        System.out.println(sort + " page");

        return "main";
    }


    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/signup")
    public String registrationPage(Model model){
        return "signup";
    }


}
