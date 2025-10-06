package apsd.quiz1.Service;

import java.math.BigInteger;
import java.util.List;

import apsd.quiz1.DTOs.DepartmentDto;
import apsd.quiz1.Domain.Department;

public interface IDepartmentService {
    void addDepartment(BigInteger departmentNo, String name);
    Department getDepartmentByDeptNo(String deptNo);
    void addEmployeeToDepartment(String deptNo, String empNo);
    void setHeadOfDepartment(String deptNo, String empNo);
    List<DepartmentDto> getAllDepartments();
}
