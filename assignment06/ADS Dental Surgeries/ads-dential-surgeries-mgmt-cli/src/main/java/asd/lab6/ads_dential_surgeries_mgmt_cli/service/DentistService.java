package asd.lab6.ads_dential_surgeries_mgmt_cli.service;

import java.util.List;

import org.springframework.stereotype.Service;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Dentist;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.DentistRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DentistService {

    private final DentistRepository dentistRepository;

    public Dentist create(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    public List<Dentist> getAll() {
        return dentistRepository.findAll();
    }

    public Dentist getById(Long id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentist not found with id " + id));
    }

    public Dentist update(Long id, Dentist updated) {
        Dentist existing = getById(id);
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setContact(updated.getContact());
        existing.setSpecialization(updated.getSpecialization());
        return dentistRepository.save(existing);
    }

    public void delete(Long id) {
        dentistRepository.deleteById(id);
    }
}