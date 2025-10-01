package apsd.lab2a.strategy;

import java.util.List;

import apsd.lab2a.domain.Employee;

public interface IReportStrategy {
    String generateReport(List<Employee> employees);
}
