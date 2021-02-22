package ua.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.restaurant.entity.Logins;

import javax.annotation.Nonnull;
import java.util.Optional;

@Repository
public interface LoginsRepository extends JpaRepository<Logins, Long> {
    Optional<Logins> findByLogin(String login);
    @Nonnull Optional<Logins> findById(@Nonnull Long id);
}
