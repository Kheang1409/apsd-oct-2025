package apsd.quiz1.DAO;

import java.util.List;

import apsd.quiz1.Domain.Department;
import apsd.quiz1.Domain.Employee;

public interface IDepartmentDAO {
    void addDepartment(Department dept);
    void addEmployeeToDepartment(String deptNo, Employee emp);
    Department getDepartmentByDeptNo(String deptNo);
    List<Department> getDepartments();
}
