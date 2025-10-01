package apsd.lab2a.service;

import java.time.LocalDate;
import java.util.List;

import apsd.lab2a.domain.Employee;
import apsd.lab2a.domain.PensionPlan;
import apsd.lab2a.strategy.IReportStrategy;

public interface IPensionPlanService {
    void saveEmployee(Employee employee);
    void setReportStrategy(IReportStrategy reportStrategy);
    void enrollEmployeeToPensionPlan(Employee employee, PensionPlan pensionPlan);
    public List<Employee> getEmployees();
    public List<Employee> getQuarterlyUpcomingEnrollees(LocalDate nextQuarterStart, LocalDate nextQuarterEnd);
    String generateReport(List<Employee> employees);
}
