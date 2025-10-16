package asd.lab9.ads_dentail_surgeries_web_api.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient extends User {
    private String firstName;
    private String lastName;
    private String contact;
    private LocalDate dateOfBirth;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
}
