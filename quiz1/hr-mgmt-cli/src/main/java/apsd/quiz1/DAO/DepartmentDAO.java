package apsd.quiz1.DAO;

import java.util.HashSet;
import java.util.List;

import apsd.quiz1.Domain.Department;
import apsd.quiz1.Domain.Employee;

public class DepartmentDAO implements IDepartmentDAO{
	private HashSet<Department> departments;

	public DepartmentDAO(){
		departments = new HashSet<>();
	}

	@Override
	public void addDepartment(Department dept) {
		departments.add(dept);
	}

	@Override
	public List<Department> getDepartments() {
		return departments.stream().toList();
	}

    @Override
    public Department getDepartmentByDeptNo(String deptNo) {
        for (Department dept : departments) {
            if (dept.getDepartmentNo().toString().equals(deptNo)) {
                return dept;
            }
        }
        return null;
    }

	@Override
	public void addEmployeeToDepartment(String deptNo, Employee empNo) {
		Department dept = getDepartmentByDeptNo(deptNo);
		if (dept == null) return;
		dept.addEmployee(empNo);
	}
}
