package asd.lab6.ads_dential_surgeries_mgmt_cli.repository.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import asd.lab6.ads_dential_surgeries_mgmt_cli.domain.Patient;
import asd.lab6.ads_dential_surgeries_mgmt_cli.repository.PatientRepository;

@Profile("test")
@Repository
public class InMemoryPatientRepository implements PatientRepository {

    private final Map<Long, Patient> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public Patient save(Patient patient) {
        if (patient.getId() == null) {
            patient.setId(idGenerator.incrementAndGet());
        }
        storage.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Patient> findAll() {
       return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}