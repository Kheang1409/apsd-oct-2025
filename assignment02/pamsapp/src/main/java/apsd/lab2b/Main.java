package apsd.lab2b;

import java.time.LocalDate;
import java.util.List;

import apsd.lab2b.dao.InMemoryPatientDAO;
import apsd.lab2b.dao.PatientDAO;
import apsd.lab2b.domain.MailingAddress;
import apsd.lab2b.domain.Patient;
import apsd.lab2b.service.IPatientService;
import apsd.lab2b.service.PatientService;
import apsd.lab2b.strategy.JsonExport;

public class Main {
    public static void main(String[] args) {
        PatientDAO patientDAO = new InMemoryPatientDAO();
        IPatientService patientService = new PatientService(patientDAO);

        patientService.savePatient(new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", new MailingAddress("1 N Street", "", "", ""), LocalDate.of(1987, 1, 19)));
        patientService.savePatient(new Patient(2, "Ana", "Smith", "", "amsith@te.edu", null, LocalDate.of(1948, 12, 5)));
        patientService.savePatient(new Patient(3, "Marcus", "Garvey", "(123) 292-0018", "", new MailingAddress("4", "East Ave", "", ""), LocalDate.of(2001, 9, 18)));
        patientService.savePatient(new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", null, LocalDate.of(1995, 2, 28)));
        patientService.savePatient(new Patient(5, "Mary", "Washington", "", "", new MailingAddress("30 W", "Burlington", "", ""), LocalDate.of(1932, 5, 31)));

        JsonExport jsonExport = new JsonExport();
        patientService.setReportStrategy(jsonExport);

        // Get patients sorted by age in descending order
        List<Patient> sortedPatients = patientService.getPatients();
        
        // Generate JSON report and display it
        String report = patientService.generateReport(sortedPatients);
        System.out.println("Generated JSON Report:");
        System.out.println(report);
        
        // Write JSON data to file
        jsonExport.writeToFile(sortedPatients, "patients_by_age.json");
    }
}