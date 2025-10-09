package asd.lab6.ads_dential_surgeries_mgmt_cli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Patient;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public Patient update(Long id, Patient updated) {
        Patient existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setContact(updated.getContact());
        existing.setDateOfBirth(updated.getDateOfBirth());
        existing.setAddress(updated.getAddress());
        return patientRepository.save(existing);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }
}