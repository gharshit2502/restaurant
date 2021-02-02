package ua.restaurant.security;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.service.LoginService;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final LoginService loginService;

    @Autowired
    private UserDetailsServiceImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostConstruct
    public void init() {
        if (!loginService.findByUserLogin("manager").isPresent()) {
            loginService.saveNewUser(LoginDTO.builder()
                    .login("manager")
                    .password("password")
                    .email("tro@gmail.com")
                    .build(), RoleType.ROLE_MANAGER);
        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Logins login = loginService.findByUserLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("Could not find user: " + username)
        );
//        if (login == null
////        || login.getValidationStatus() == 0
//        ) {
//            throw new UsernameNotFoundException("Could not find user: " + username);
//        }
        log.info(login.getLogin());
        log.info(login.getPassword());

        return new UserDetailsImpl(login);
    }

}
