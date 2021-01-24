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
    @RequestMapping("/api")
    public String mainPage(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return "main";
    }

    @RequestMapping("/")
    public String mainPage1(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
//        Object o = auth.getDetails();
//        SecurityContextHolder.getContext()

        return "index";
    }

//    @RequestMapping("/login")
//    public class loginPage {
//        @GetMapping
//        public String getLogin() {
//            return "login";
//        }
//    }

//    @RequestMapping("/login")
//    public String loginPage(@RequestParam(value = "error", required = false) String error,
//                            @RequestParam(value = "logout", required = false) String logout,
//                            Model model){
//        model.addAttribute("error", error != null);
//        model.addAttribute("logout", logout != null);
//        return "login";
//    }

//    @GetMapping("/register")
//    public String registerForm(Model model){
//        return "views/registerForm";
//    }


//    @RequestMapping("/api")
//    public String mainPage(){
//        return "index.html";
//    }

    @RequestMapping("/all_user")
    public String userPage(){
        return "users/index.html";
    }
}
