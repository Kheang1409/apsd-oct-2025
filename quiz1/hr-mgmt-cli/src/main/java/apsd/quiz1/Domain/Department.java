package apsd.quiz1.Domain;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;

public class Department {
    private BigInteger departmentNo;
    private String name;
    private HashSet<Employee> employees = new HashSet<>();
    private Employee headOfEmployee;

    public Department(BigInteger departmentNo, String name) {
        this.departmentNo = departmentNo;
        this.name = name;
    }

    public BigInteger getDepartmentNo() {
        return departmentNo;
    }
    public String getName() {
        return name;
    }
    public List<Employee> getEmployees() {
        return employees.stream().toList();
    }
    public void addEmployee(Employee emp){
        this.employees.add(emp);
    }

    public Employee getHeadOfEmployee() {
        return headOfEmployee;
    }

    public void setHeadOfEmployee(Employee headOfEmployee) {
        this.headOfEmployee = headOfEmployee;
    }
}
