package apsd.lab2b.service;

import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import apsd.lab2b.dao.PatientDAO;
import apsd.lab2b.domain.Patient;
import apsd.lab2b.proxy.Logging;
import apsd.lab2b.strategy.IReportStrategy;

public class PatientService implements IPatientService{
    private PatientDAO employeeDAO;
    private IReportStrategy exportStrategy ;

    public PatientService(PatientDAO employeeDAO) {

        var classLoader = employeeDAO.getClass().getClassLoader();
        this.employeeDAO = (PatientDAO) Proxy.newProxyInstance(
            classLoader,
            new Class[] { PatientDAO.class },
            new Logging(employeeDAO)
        );

    }

    @Override
    public void setReportStrategy(IReportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy ;
    }

    @Override
    public void savePatient(Patient employee) {
        employeeDAO.savePatient(employee);
    }
    @Override
    public List<Patient> getPatients() {
        return employeeDAO.getAllPatients().stream()
            .sorted(Comparator.comparing(Patient::getAge).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public String generateReport(List<Patient> patients) {
        return exportStrategy.generateReport(patients);
    }
}
