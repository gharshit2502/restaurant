package ua.restaurant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.service.LoginService;

//@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginController {

    private final LoginService loginService;
    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    //@RequestMapping(value = "login", method = RequestMethod.POST)
//    @PostMapping(value = "login")
//    public void loginController(LoginDTO user){
//        log.info("{}", accountsService.findByUserLogin(user));
//        log.info("{}", user);
////        userService.saveNewUser(User.builder()
////                .firstName("Ann")
////                .lastName("Reizer")
////                .email("AnnReizer@testing.ua")
////                .role(RoleType.ROLE_USER)
////                .build());
//    }

//    @RequestMapping(value = "user", method = RequestMethod.GET)
//    public LoginsDTO getAllUser() {
//        log.info("{}", accountsService.getAllUsers());
//        return accountsService.getAllUsers();
//    }
}
