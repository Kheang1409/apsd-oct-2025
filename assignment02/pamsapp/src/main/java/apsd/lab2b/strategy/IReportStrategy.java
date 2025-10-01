package apsd.lab2b.strategy;

import java.util.List;

import apsd.lab2b.domain.Patient;

public interface IReportStrategy {
    String generateReport(List<Patient> patients);
}
