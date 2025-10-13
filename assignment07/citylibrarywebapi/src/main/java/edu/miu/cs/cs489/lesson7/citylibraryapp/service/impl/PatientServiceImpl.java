package edu.miu.cs.cs489.lesson7.citylibraryapp.service.impl;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Address;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;
import edu.miu.cs.cs489.lesson7.citylibraryapp.repository.PatientRepository;
import edu.miu.cs.cs489.lesson7.citylibraryapp.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .sorted((p1, p2) -> (p1.getLastName() == null ? "" : p1.getLastName())
                        .compareToIgnoreCase(p2.getLastName() == null ? "" : p2.getLastName()))
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with id " + id + " not found"));
    }

    @Override
    public PatientResponse addNewPatient(PatientRequest request) {
        Patient p = new Patient();
        p.setFirstName(request.firstName());
        p.setLastName(request.lastName());
        p.setEmail(request.email());
        p.setPhone(request.phone());
        if (request.primaryAddress() != null) {
            AddressRequest ar = request.primaryAddress();
            Address a = new Address(null, ar.street(), ar.city(), ar.state(), ar.zipCode());
            p.setPrimaryAddress(a);
        }
        Patient saved = patientRepository.save(p);
        return toResponse(saved);
    }

    @Override
    public Patient updatePatient(Integer id, PatientRequest editedPatient) {
        Patient existing = getPatientById(id);
        if (editedPatient.firstName() != null) existing.setFirstName(editedPatient.firstName());
        if (editedPatient.lastName() != null) existing.setLastName(editedPatient.lastName());
        if (editedPatient.email() != null) existing.setEmail(editedPatient.email());
        if (editedPatient.phone() != null) existing.setPhone(editedPatient.phone());
        if (editedPatient.primaryAddress() != null) {
            AddressRequest ar = editedPatient.primaryAddress();
            Address a = new Address(null, ar.street(), ar.city(), ar.state(), ar.zipCode());
            existing.setPrimaryAddress(a);
        }
        return patientRepository.save(existing);
    }

    @Override
    public void deletePatientById(Integer id) {
        patientRepository.deleteById(id);
    }

    @Override
    public List<Patient> searchPatient(String searchString) {
        return patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                searchString, searchString, searchString);
    }

    private PatientResponse toResponse(Patient p) {
        AddressResponse ar = null;
        if (p.getPrimaryAddress() != null) {
            Address a = p.getPrimaryAddress();
            ar = new AddressResponse(a.getAddressId(), a.getStreet(), a.getCity(), a.getState(), a.getZipCode());
        }
        return new PatientResponse(p.getPatientId(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhone(), ar);
    }
}
