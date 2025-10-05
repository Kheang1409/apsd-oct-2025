package apsd.quiz1.Service;

import java.time.LocalDate;

import apsd.quiz1.Domain.Employee;

public interface IEmployeeService {
    void addEmployee(String employeeNo,
        String lastName,
        String firstName,
        LocalDate dateOfDate,
        LocalDate dateOfEmployment,
        double biweeklySalary);
        Employee getEmployeeByEmpNo(String empNo);
}
