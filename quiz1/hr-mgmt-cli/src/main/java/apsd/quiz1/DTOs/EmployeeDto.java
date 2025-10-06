package apsd.quiz1.DTOs;

import apsd.quiz1.Domain.Employee;

public record EmployeeDto(
    String employeeNo,
    String firstName,
    String lastName,
    int yearsOfEmployment,
    double biweeklySalary
) {
    public EmployeeDto(Employee employee) {
        this(
            employee.getEmployeeNo().toString(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getYearsOfEmployment(),
            employee.getBiweeklySalary()
        );
    }
}
