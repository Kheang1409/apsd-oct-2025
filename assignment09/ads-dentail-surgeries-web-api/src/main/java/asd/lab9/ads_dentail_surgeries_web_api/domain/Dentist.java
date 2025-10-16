package asd.lab9.ads_dentail_surgeries_web_api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dentists")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Dentist extends User {
    private String firstName;
    private String lastName;
    private String contact;
    private String specialization;
}
