package apsd.quiz1.DTOs;

import java.util.List;

import apsd.quiz1.Domain.Department;

public record DepartmentDto (
    String departmentNo,
    String name,
    EmployeeDto headOfEmployee,
    double totalSalary,
    List<EmployeeDto> employees
 ){
    public DepartmentDto(Department department) {
        this(
            department.getDepartmentNo().toString(),
            department.getName(),
            department.getHeadOfEmployee() != null ? new EmployeeDto(department.getHeadOfEmployee()) : null,
            department.getTotalSalary(),
            department.getEmployees() != null ? department.getEmployees().stream().map(EmployeeDto::new).toList() : List.of()
        );
    }
}
