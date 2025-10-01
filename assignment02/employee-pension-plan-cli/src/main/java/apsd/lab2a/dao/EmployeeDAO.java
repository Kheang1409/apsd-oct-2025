package apsd.lab2a.dao;

import java.util.List;

import apsd.lab2a.domain.Employee;

public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);
}
