package ua.restaurant.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.dto.LoginsDTO;
import ua.restaurant.entity.Login;
import ua.restaurant.repository.LoginRepository;

import java.util.Optional;

@Slf4j
@Service
public class LoginService {
    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LoginsDTO getAllUsers() {
        // TODO checking for an empty user list
        return new LoginsDTO(loginRepository.findAll());
    }

    public Optional<Login> findByUserLogin (LoginDTO loginDTO){
        // TODO check for user availability. password check
        return loginRepository.findByEmail(loginDTO.getEmail());
    }

    public void saveNewUser (Login login){
        // TODO inform the user about the replay email
        // TODO exception to endpoint
        try {
            loginRepository.save(login);
        } catch (Exception ex){
            log.info("{Почтовый адрес уже существует}");
        }

    }


}
