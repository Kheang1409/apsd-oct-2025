package asd.lab9.ads_dentail_surgeries_web_api.config;

import asd.lab9.ads_dentail_surgeries_web_api.domain.Role;
import asd.lab9.ads_dentail_surgeries_web_api.domain.User;
import asd.lab9.ads_dentail_surgeries_web_api.repository.RoleRepository;
import asd.lab9.ads_dentail_surgeries_web_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seed(RoleRepository roles, UserRepository users, PasswordEncoder encoder) {
        return args -> {
            Role patient = roles.findByName("PATIENT").orElseGet(() -> roles.save(Role.builder().name("PATIENT").build()));
            Role dentist = roles.findByName("DENTIST").orElseGet(() -> roles.save(Role.builder().name("DENTIST").build()));
            Role manager = roles.findByName("OFFICE_MANAGER").orElseGet(() -> roles.save(Role.builder().name("OFFICE_MANAGER").build()));

            if (!users.existsByUsername("admin")) {
                User admin = User.builder().username("admin").password(encoder.encode("admin123")).build();
                admin.getRoles().add(manager);
                users.save(admin);
            }
        };
    }
}
