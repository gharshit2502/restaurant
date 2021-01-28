package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.restaurant.entity.Logins;

import java.util.Optional;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Long> {
    Optional<Logins> findByEmail(String email);
    Optional<Logins> findByLogin(String login);
    Optional<Logins> findById(Long id);
}
