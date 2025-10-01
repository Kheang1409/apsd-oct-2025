package apsd.lab2b.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import apsd.lab2b.domain.Patient;

public class InMemoryPatientDAO implements PatientDAO {

    private Hashtable<Long, Patient> patients = new Hashtable<>();

    @Override
    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients.values());
    }

    @Override
    public void savePatient(Patient patient) {
        patients.put(patient.getId(), patient);
    }
}
