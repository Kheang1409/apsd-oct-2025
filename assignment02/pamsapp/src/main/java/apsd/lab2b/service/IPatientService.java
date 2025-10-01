package apsd.lab2b.service;

import java.util.List;

import apsd.lab2b.domain.Patient;
import apsd.lab2b.strategy.IReportStrategy;

public interface IPatientService {
    void savePatient(Patient employee);
    void setReportStrategy(IReportStrategy reportStrategy);
    public List<Patient> getPatients();
    String generateReport(List<Patient> employees);
}
