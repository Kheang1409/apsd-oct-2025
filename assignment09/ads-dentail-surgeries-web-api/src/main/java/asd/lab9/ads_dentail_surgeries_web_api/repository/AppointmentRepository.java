package asd.lab9.ads_dentail_surgeries_web_api.repository;

import asd.lab9.ads_dentail_surgeries_web_api.domain.Appointment;
import asd.lab9.ads_dentail_surgeries_web_api.domain.Dentist;
import asd.lab9.ads_dentail_surgeries_web_api.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDentist(Dentist dentist);
    List<Appointment> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
