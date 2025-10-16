package asd.lab9.ads_dentail_surgeries_web_api.repository;

import asd.lab9.ads_dentail_surgeries_web_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
