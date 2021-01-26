package ua.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.restaurant.entity.Login;
import ua.restaurant.entity.RoleType;
import ua.restaurant.repository.LoginRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Controller
@RequestMapping("/signup")
public class SignUpController {
//    @Autowired
//    private Token token;
//    @Autowired
//    private MailSender mailSender;
    @Autowired
    private LoginRepository loginRepository;

    @PostMapping
    public void postSignUp(@RequestBody Login login, HttpServletResponse response) throws IOException {
        if (!loginRepository.findByLogin(login.getLogin()).isPresent()) {
            login.setRole(RoleType.ROLE_CUSTOMER);
            login.setPassword(new BCryptPasswordEncoder().encode(login.getPassword()));
//            login.setPassword(BCrypt.hashpw(login.getPassword(), BCrypt.gensalt()));
//            user.setToken(token.getJWTToken(user.getLogin()));
//            user.setValidationStatus(0);
            loginRepository.save(login);
//            mailSender.sendMailConfirmation(user);
//            response.sendRedirect("/");

        } else {
            response.sendError(111, "pipec");
        }
        response.encodeRedirectURL("/login");
    }

}
