package apsd.quiz1.DAO;

import java.util.HashSet;
import apsd.quiz1.Domain.Employee;

public class EmployeeDAO implements IEmployeeDAO{
    
    private HashSet<Employee> employees;

    public EmployeeDAO(){
        employees = new HashSet<>();
    }

    @Override
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    // helper for quick debugging/inspection
    public int size() {
        return employees.size();
    }

    @Override
    public Employee getEmployeeByEmpNo(String empNo) {
        for(Employee emp: employees){
            if(empNo.equals(emp.getEmployeeNo())){
                return emp;
            }
        }
        return null;
    }


}
