package apsd.quiz1.Service;

import apsd.quiz1.DAO.EmployeeDAO;
import apsd.quiz1.DAO.IEmployeeDAO;
import apsd.quiz1.Domain.Employee;
import java.time.LocalDate;

public class EmployeeService implements IEmployeeService {
    private IEmployeeDAO employeeDAO = new EmployeeDAO();

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
    }

    @Override
    public void addEmployee(
        String employeeNo,
        String lastName,
        String firstName,
        LocalDate dateOfDate,
        LocalDate dateOfEmployment,
        double biweeklySalary) {

        Employee emp = new Employee.Builder()
                .employeeNo(employeeNo)
                .firstName(firstName)
                .lastName(lastName)
                .dateOfEmployment(dateOfEmployment)
                .biweeklySalary(biweeklySalary)
                .build();

        employeeDAO.addEmployee(emp);
    }

    @Override
    public Employee getEmployeeByEmpNo(String empNo) {
        return employeeDAO.getEmployeeByEmpNo(empNo);
    }
}
