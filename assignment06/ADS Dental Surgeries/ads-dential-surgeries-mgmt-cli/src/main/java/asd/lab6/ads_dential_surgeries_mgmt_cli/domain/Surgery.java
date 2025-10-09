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
@Table(name = "surgeries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String telephoneNumber;

    @OneToOne
    @JoinColumn(name = "address_id", unique = true)
    private Address address;
}