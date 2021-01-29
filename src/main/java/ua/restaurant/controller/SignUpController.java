package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.service.LoginService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@Slf4j
@Controller
@RequestMapping("/signup")
public class SignUpController {
    private final LoginService loginService;

    @Autowired
    public SignUpController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public void postSignUp(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws IOException {

        // TODO validation input

        try {
            loginService.saveNewUser(loginDTO, RoleType.ROLE_CUSTOMER);
            response.sendRedirect("login");
        } catch (NoSuchElementException e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
        }
    }

}
