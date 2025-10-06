package apsd.quiz1.DTOs;

import apsd.quiz1.Domain.Employee;

public class EmployeeDto {
    public String employeeNo;
    public String firstName;
    public String lastName;
    public int yearsOfEmployment;
    public double biweeklySalary;

    public EmployeeDto(Employee employee) {
        if (employee == null) return;
        this.employeeNo = employee.getEmployeeNo().toString();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.yearsOfEmployment = employee.getYearsOfEmployment();
        this.biweeklySalary = employee.getBiweeklySalary();
    }
}
