package apsd.lab2b;

import java.time.LocalDate;

import apsd.lab2b.dao.InMemoryPatientDAO;
import apsd.lab2b.dao.PatientDAO;
import apsd.lab2b.domain.Patient;
import apsd.lab2b.service.IPatientService;
import apsd.lab2b.service.PatientService;
import apsd.lab2b.strategy.JsonExport;

public class Main {
    public static void main(String[] args) {
        PatientDAO patientDAO = new InMemoryPatientDAO();
        IPatientService patientService = new PatientService(patientDAO);

        patientService.savePatient(new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", "1 N Street", LocalDate.of(1987, 1, 19)));
        patientService.savePatient(new Patient(2, "Ana", "Smith", "", "amsith@te.edu", "", LocalDate.of(1948, 12, 5)));
        patientService.savePatient(new Patient(3, "Marcus", "Garvey", "(123) 292-0018", "", "4 East Ave", LocalDate.of(2001, 9, 18)));
        patientService.savePatient(new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", "", LocalDate.of(1995, 2, 28)));
        patientService.savePatient(new Patient(5, "Mary", "Washington", "", "", "30 W Burlington", LocalDate.of(1932, 5, 31)));

        patientService.setReportStrategy(new JsonExport());

        String report = patientService.generateReport(patientService.getPatients());
        System.out.println(report);
    }
}