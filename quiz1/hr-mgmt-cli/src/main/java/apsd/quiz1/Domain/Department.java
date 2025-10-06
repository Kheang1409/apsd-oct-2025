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
        return employees.stream()
            .sorted((e1, e2) -> {
                int cmp = Integer.compare(e2.getYearsOfEmployment(), e1.getYearsOfEmployment());
                if (cmp == 0) {
                    cmp = e1.getLastName().compareToIgnoreCase(e2.getLastName());
                }
                return cmp;
            })
            .toList();
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
    public double getTotalSalary(){
         // Calculates the total annual salary for all employees in the department
         // Assumes getBiweeklySalary() returns the biweekly salary
         // There are 26 biweekly periods in a year
         return employees.stream().mapToDouble(e -> e.getBiweeklySalary() * 26).sum();
    }
}
