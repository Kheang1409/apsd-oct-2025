package asd.lab9.ads_dentail_surgeries_web_api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "surgeries")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
