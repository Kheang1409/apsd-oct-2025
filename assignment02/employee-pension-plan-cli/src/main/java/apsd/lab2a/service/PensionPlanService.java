package apsd.lab2a.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import apsd.lab2a.dao.EmployeeDAO;
import apsd.lab2a.domain.Employee;
import apsd.lab2a.domain.PensionPlan;
import apsd.lab2a.strategy.IReportStrategy;

public class PensionPlanService implements IPensionPlanService{
    private EmployeeDAO employeeDAO;
    private IReportStrategy exportStrategy ;

    public PensionPlanService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public void setReportStrategy(IReportStrategy exportStrategy) {
        this.exportStrategy = exportStrategy ;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }
    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.getAllEmployees();
    }
    @Override
    public void enrollEmployeeToPensionPlan(Employee employee, PensionPlan pensionPlan) {
        employee.setPensionPlan(pensionPlan);
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public List<Employee> getQuarterlyUpcomingEnrollees(LocalDate nextQuarterStart, LocalDate nextQuarterEnd) {
       return employeeDAO.getAllEmployees().stream()
            .filter(e -> !e.isEnrolled() && e.isEligibleForPension(nextQuarterStart, nextQuarterEnd))
            .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public String generateReport(List<Employee> employees) {
        return exportStrategy.generateReport(employees);
    }
}
