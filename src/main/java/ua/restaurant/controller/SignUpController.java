package ua.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.RoleType;
import ua.restaurant.service.LoginService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public boolean postSignUp(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws IOException {

        // TODO validation input

        try {
            log.info("Registration of user: " + loginDTO.toString());
            loginService.saveNewUser(loginDTO, RoleType.ROLE_CUSTOMER);
            response.sendRedirect("login");
            return true;
        } catch (Exception e) {
            response.sendError(HttpStatus.BAD_REQUEST.value());
            log.warn("Cannot make registration");
            return false;
        }
    }

}
