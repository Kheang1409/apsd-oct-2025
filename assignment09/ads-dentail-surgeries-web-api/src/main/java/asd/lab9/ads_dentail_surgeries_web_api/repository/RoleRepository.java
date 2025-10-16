package asd.lab9.ads_dentail_surgeries_web_api.repository;

import asd.lab9.ads_dentail_surgeries_web_api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
