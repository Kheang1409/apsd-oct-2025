package asd.lab9.ads_dentail_surgeries_web_api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bills")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double amount;
    private String status;

    @OneToOne
    @JoinColumn(name = "appointment_id", unique = true)
    private Appointment appointment;
}
