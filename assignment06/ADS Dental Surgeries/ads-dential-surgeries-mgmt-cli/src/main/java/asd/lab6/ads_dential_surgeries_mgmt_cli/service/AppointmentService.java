package asd.lab6.ads_dential_surgeries_mgmt_cli.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Appointment;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.AppointmentRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.DentistRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.PatientRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.ServiceRepository;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.SurgeryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DentistRepository dentistRepository;
    private final ServiceRepository serviceRepository;
    private final SurgeryRepository surgeryRepository;

    public Appointment createAppointment(Long patientId, Long dentistId, Long serviceId, Long surgeryId, LocalDateTime dateTime) {
        Appointment appointment = new Appointment();
        appointment.setDateTime(dateTime);
        appointment.setStatus("Scheduled");
        appointment.setPatient(patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found")));
        appointment.setDentist(dentistRepository.findById(dentistId)
                .orElseThrow(() -> new RuntimeException("Dentist not found")));
        appointment.setService(serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found")));
        appointment.setSurgery(surgeryRepository.findById(surgeryId)
                .orElseThrow(() -> new RuntimeException("Surgery not found")));

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void cancelAppointment(Long id) {
        Appointment appointment = getById(id);
        appointment.setStatus("Cancelled");
        appointmentRepository.save(appointment);
    }
}