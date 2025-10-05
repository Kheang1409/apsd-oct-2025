package apsd.quiz1.Service;

import java.math.BigInteger;
import java.util.List;

import apsd.quiz1.DAO.DepartmentDAO;
import apsd.quiz1.DAO.EmployeeDAO;
import apsd.quiz1.DAO.IDepartmentDAO;
import apsd.quiz1.DAO.IEmployeeDAO;
import apsd.quiz1.Domain.Department;
import apsd.quiz1.Domain.Employee;

public class DepartmentService implements IDepartmentService{
	IDepartmentDAO departmentDAO;
	IEmployeeService employeeService;

	public DepartmentService(IEmployeeService employeeService)
	{
		departmentDAO = new DepartmentDAO();
		this.employeeService = employeeService;
	}
	@Override
	public void addDepartment(BigInteger departmentNo, String name) {
		Department dept = new Department(departmentNo, name);
		departmentDAO.addDepartment(dept);
	}

	@Override
	public Department getDepartmentByDeptNo(String deptNo) {
		return departmentDAO.getDepartmentByDeptNo(deptNo);
	}

	@Override
	public void addEmployeeToDepartment(String deptNo, String empNo) {
		if (deptNo == null || empNo == null) return;
		Employee emp = employeeService.getEmployeeByEmpNo(empNo);
		if (emp == null) return;
		departmentDAO.addEmployeeToDepartment(deptNo, emp);
	}

	@Override
	public void setHeadOfDepartment(String deptNo, String empNo) {
		if (deptNo == null || empNo == null) return;
		Employee emp = employeeService.getEmployeeByEmpNo(empNo);
		if (emp == null) return;
		Department dept = departmentDAO.getDepartmentByDeptNo(deptNo);
		if (dept == null) return;
		dept.setHeadOfEmployee(emp);
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentDAO.getDepartments();
	}
}
