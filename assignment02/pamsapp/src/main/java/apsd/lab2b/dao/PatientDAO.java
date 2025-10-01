package apsd.lab2b.dao;

import java.util.List;

import apsd.lab2b.domain.Patient;

public interface PatientDAO {
    List<Patient> getAllPatients();
    void savePatient(Patient employee);
}
