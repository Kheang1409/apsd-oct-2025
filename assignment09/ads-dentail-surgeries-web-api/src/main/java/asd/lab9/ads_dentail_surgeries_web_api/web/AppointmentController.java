package asd.lab9.ads_dentail_surgeries_web_api.web;

import asd.lab9.ads_dentail_surgeries_web_api.domain.Appointment;
import asd.lab9.ads_dentail_surgeries_web_api.repository.AppointmentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PATIENT','DENTIST','OFFICE_MANAGER')")
    public List<Appointment> list() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('OFFICE_MANAGER','DENTIST')")
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentRepository.save(appointment));
    }
}
