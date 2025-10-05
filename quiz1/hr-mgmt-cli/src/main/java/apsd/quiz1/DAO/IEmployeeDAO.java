package apsd.quiz1.DAO;

import apsd.quiz1.Domain.Employee;

public interface IEmployeeDAO {
    void addEmployee(Employee emp);
    Employee getEmployeeByEmpNo(String empNo);
}
