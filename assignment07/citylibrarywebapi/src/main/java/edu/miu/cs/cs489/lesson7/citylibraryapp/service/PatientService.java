package edu.miu.cs.cs489.lesson7.citylibraryapp.service;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getAllPatients();

    Patient getPatientById(Integer id);

    PatientResponse addNewPatient(PatientRequest request);

    Patient updatePatient(Integer id, edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest editedPatient);

    void deletePatientById(Integer id);

    List<Patient> searchPatient(String searchString);
}
