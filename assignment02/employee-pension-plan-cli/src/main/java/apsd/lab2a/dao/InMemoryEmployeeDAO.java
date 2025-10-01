package apsd.lab2a.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import apsd.lab2a.domain.Employee;

public class InMemoryEmployeeDAO implements EmployeeDAO {

    private Hashtable<Long, Employee> employees = new Hashtable<>();

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public void saveEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employees.containsKey(employee.getId())) {
            employees.put(employee.getId(), employee);  
        } else {
            System.out.println("Employee with ID " + employee.getId() + " not found for update.");
        }
    }
}
