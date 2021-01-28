package ua.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.repository.LoginsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/signup")
public class SignUpController {
//    @Autowired
//    private Token token;
//    @Autowired
//    private MailSender mailSender;
    @Autowired
    private LoginsRepository loginsRepository;

    @PostMapping
    public void postSignUp(@RequestBody Logins login, HttpServletResponse response) throws IOException {
        if (!loginsRepository.findByLogin(login.getLogin()).isPresent()) {
            login.setRole(RoleType.ROLE_CUSTOMER);
            login.setPassword(new BCryptPasswordEncoder().encode(login.getPassword()));
//            login.setPassword(BCrypt.hashpw(login.getPassword(), BCrypt.gensalt()));
//            user.setToken(token.getJWTToken(user.getLogin()));
//            user.setValidationStatus(0);
            loginsRepository.save(login);
//            mailSender.sendMailConfirmation(user);
//            response.sendRedirect("/");

        } else {
            response.sendError(111, "pipec");
        }
        response.encodeRedirectURL("/login");
    }

}
