package ua.restaurant.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.repository.LoginsRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class LoginService {
    private final LoginsRepository loginsRepository;
    @Autowired
    public LoginService(LoginsRepository loginsRepository) {
        this.loginsRepository = loginsRepository;
    }

    public Optional<Logins> findByUserLogin (@NonNull String login){
        // TODO check for user availability. password check
        return loginsRepository.findByLogin(login);
    }

    public Optional<Logins> findById (@NonNull Long id) {
        return loginsRepository.findById(id);
    }

    public Logins saveNewUser (@NonNull LoginDTO loginDTO, RoleType role) throws NoSuchElementException {
        try {
            return loginsRepository.save(Logins.builder()
                    .login(loginDTO.getLogin())
                    .email(loginDTO.getEmail())
                    .role(role)
                    .time(Timestamp.valueOf(LocalDateTime.now()))
                    .password(new BCryptPasswordEncoder().encode(loginDTO.getPassword())).build());
        } catch (Exception e){
            log.info("Логин уже существует: " + loginDTO.getLogin());
            throw new NoSuchElementException();
        }
    }

}
