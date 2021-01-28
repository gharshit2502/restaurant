package ua.restaurant.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.dto.LoginsDTO;
import ua.restaurant.entity.Logins;
import ua.restaurant.repository.LoginsRepository;

import java.util.Optional;

@Slf4j
@Service
public class AccountsService {
    private final LoginsRepository loginsRepository;

    @Autowired
    public AccountsService(LoginsRepository loginsRepository) {
        this.loginsRepository = loginsRepository;
    }

    public LoginsDTO getAllUsers() {
        // TODO checking for an empty user list
        return new LoginsDTO(loginsRepository.findAll());
    }

    public Optional<Logins> findByUserLogin (LoginDTO loginDTO){
        // TODO check for user availability. password check
        return loginsRepository.findByEmail(loginDTO.getEmail());
    }

    public Optional<Logins> findByUserLogin (@NonNull String login){
        // TODO check for user availability. password check
        return loginsRepository.findByLogin(login);
    }

    public Optional<Logins> findById (@NonNull Long id) {
        return loginsRepository.findById(id);
    }

    public void saveNewUser (@NonNull Logins login){
        // TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            loginsRepository.save(login);
        } catch (Exception ex){
            log.info("{Логин уже существует}");
        }

    }

    public void save(@NonNull Logins login) {
        loginsRepository.save(login);
    }

}
