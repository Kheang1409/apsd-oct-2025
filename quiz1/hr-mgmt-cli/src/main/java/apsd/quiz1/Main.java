package apsd.quiz1;

import apsd.quiz1.Service.DepartmentService;
import apsd.quiz1.Service.EmployeeService;
import apsd.quiz1.Service.IDepartmentService;
import apsd.quiz1.Service.IEmployeeService;
import apsd.quiz1.Utility.Export;
import apsd.quiz1.Domain.Department;
import apsd.quiz1.DTOs.DepartmentDto;

import java.time.LocalDate;
import java.util.List;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        IEmployeeService empService = new EmployeeService();
        IDepartmentService deptService = new DepartmentService(empService);


        // create some departments (1,2,3)
        deptService.addDepartment(new BigInteger("31288741190182539912" ), "Sales");
        deptService.addDepartment(new BigInteger("29274582650152771644" ), "Marketing");
        deptService.addDepartment(new BigInteger("29274599650152771609" ), "Engineering");

        // sample employees (employeeNo, lastName, firstName, dateOfDate, dateOfEmployment, biweeklySalary, isHead)
        empService.addEmployee("000-11-1234", "Philips", "Michael",
                LocalDate.of(1995, 12, 31), LocalDate.of(2021, 10, 15), 2750.50);

        empService.addEmployee("000-61-0987", "Smith", "Anna",
                LocalDate.of(1981, 9, 17), LocalDate.of(2022, 8, 21), 2500.00);

        empService.addEmployee("000-99-1766", "Hammonds", "John",
                LocalDate.of(2001, 7, 15), LocalDate.of(2025, 1, 23), 1560.75);

        empService.addEmployee("000-41-1768", "Jones", "Barbara",
                LocalDate.of(2000, 11, 18), LocalDate.of(2025, 3, 13), 1650.55);

        // assign employees to departments (last value in sample lines: dept no)
        deptService.addEmployeeToDepartment("29274599650152771609", "000-11-1234");
        deptService.addEmployeeToDepartment("29274582650152771644", "000-61-0987");
        deptService.addEmployeeToDepartment("31288741190182539912", "000-99-1766");
        deptService.addEmployeeToDepartment("29274582650152771644", "000-41-1768");

        deptService.setHeadOfDepartment("29274582650152771644", "000-61-0987");
        deptService.setHeadOfDepartment("29274599650152771609", "000-11-1234");

        List<DepartmentDto> depts = deptService.getAllDepartments();
        System.out.println(Export.exportDataAsJSON(depts));
}
}