package apsd.quiz1.DTOs;

import java.util.List;

import apsd.quiz1.Domain.Department;

public class DepartmentDto {
    public String departmentNo;
    public String name;
    public EmployeeDto headOfEmployee;
    public double totalSalary;
    public List<EmployeeDto> employees;

    public DepartmentDto(Department department) {
        if (department == null) return;
        this.departmentNo = department.getDepartmentNo().toString();
        this.name = department.getName();
        this.totalSalary = department.getTotalSalary();
        if (department.getHeadOfEmployee() != null) {
            this.headOfEmployee = new EmployeeDto(department.getHeadOfEmployee());
        }
        if (department.getEmployees() != null) {
            this.employees = department.getEmployees().stream()
                .map(EmployeeDto::new)
                .toList();
        }
    }
}
