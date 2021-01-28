package ua.restaurant.security;

//import google.common.collect.ImmutableList;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.service.AccountsService;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountsService accountsService;

    @PostConstruct
    public void init() {
//        loginService.findByUserLogin("manager").ifPresent(login -> {
//                    login.setPassword(new BCryptPasswordEncoder().encode("password"));
//                    loginService.save(login);
//                });

        if (!accountsService.findByUserLogin("manager").isPresent()) {
            accountsService.saveNewUser(Logins.builder()
                    .login("manager")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .email("tro@gmail.com")
                    .role(RoleType.ROLE_MANAGER)
                    .time(Timestamp.valueOf(LocalDateTime.now()))
                    .build());
        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        System.out.println(username);
        Logins login = accountsService.findByUserLogin(username).orElseThrow(
                () -> new UsernameNotFoundException("Could not find user: " + username)
        );
//        if (login == null
////        || login.getValidationStatus() == 0
//        ) {
//            throw new UsernameNotFoundException("Could not find user: " + username);
//        }
        System.out.println(login.getLogin());
        System.out.println(login.getPassword());
        return new UserDetailsImpl(login);
    }

//    public Optional<Login> findById(Long id) {
//        return loginService.findById(id);
//    }
}
