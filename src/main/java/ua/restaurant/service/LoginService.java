package ua.restaurant.service;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.restaurant.dto.LoginDTO;
import ua.restaurant.entity.Logins;
import ua.restaurant.entity.RoleType;
import ua.restaurant.repository.LoginsRepository;
import ua.restaurant.utils.Constants;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoginService {
    private final LoginsRepository loginsRepository;
    @Autowired
    public LoginService(LoginsRepository loginsRepository) {
        this.loginsRepository = loginsRepository;
    }

    /**
     * For creating manager in the first place
     * @param login String login
     * @return Login object
     */
    public Optional<Logins> findByUserLogin (@NonNull String login){
        return loginsRepository.findByLogin(login);
    }

    /**
     * Registration
     * @param loginDTO parameters of new user
     * @param role role
     * @return Saved user
     * @throws NoSuchElementException
     *          if login already exists in database
     */
    public Logins saveNewUser (@NonNull LoginDTO loginDTO, RoleType role) throws NoSuchElementException {
        try {
            return loginsRepository.save(Logins.builder()
                    .login(loginDTO.getLogin())
                    .email(loginDTO.getEmail())
                    .role(role)
                    .time(LocalDateTime.now())
                    .password(new BCryptPasswordEncoder().encode(loginDTO.getPassword())).build());
        } catch (Exception e) {
            throw new NoSuchElementException(Constants.LOGIN_EXISTS + loginDTO.getLogin());
        }
    }

}
