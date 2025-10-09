package asd.lab6.ads_dential_surgeries_mgmt_cli.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "office_managers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OfficeManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String contact;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
