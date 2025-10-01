package apsd.lab2a;

import java.time.LocalDate;
import java.time.Month;

import apsd.lab2a.dao.EmployeeDAO;
import apsd.lab2a.dao.InMemoryEmployeeDAO;
import apsd.lab2a.domain.Employee;
import apsd.lab2a.domain.PensionPlan;
import apsd.lab2a.service.IPensionPlanService;
import apsd.lab2a.service.PensionPlanService;
import apsd.lab2a.strategy.JsonExport;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new InMemoryEmployeeDAO();
        IPensionPlanService pensionPlanService = new PensionPlanService(employeeDAO);

        // Creating Employee objects with dummy data
        Employee e1 = new Employee(1, "Daniel", "Agar", LocalDate.of(2023, 1, 17), 105945.50);
        Employee e2 = new Employee(2, "Benard", "Shaw", LocalDate.of(2022, 9, 3), 197750.00);
        Employee e3 = new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75);
        Employee e4 = new Employee(4, "Wesley", "Schneider", LocalDate.of(2023, 7, 21), 74500.00);
        Employee e5 = new Employee(5, "Anna", "Wiltord", LocalDate.of(2023, 3, 15), 85750.00);
        Employee e6 = new Employee(6, "Yosef", "Tesfalem", LocalDate.of(2024, 10, 31), 100000.00);

        // Saving Employee data
        pensionPlanService.saveEmployee(e1);
        pensionPlanService.saveEmployee(e2);
        pensionPlanService.saveEmployee(e3);
        pensionPlanService.saveEmployee(e4);
        pensionPlanService.saveEmployee(e5);
        pensionPlanService.saveEmployee(e6);

        // Creating Pension Plan objects with dummy data
        PensionPlan plan1 = new PensionPlan("EX1089", null, 100.00, e1);
        PensionPlan plan2 = new PensionPlan("", LocalDate.of(2025, 9, 3), 0, e2);
        PensionPlan plan3 = new PensionPlan("SM2307", LocalDate.of(2017, 5, 17), 1555.50, e3);

        // Enrolling Employees to their respective Pension Plans
        pensionPlanService.enrollEmployeeToPensionPlan(e1, plan1); 
        pensionPlanService.enrollEmployeeToPensionPlan(e2, plan2);
        pensionPlanService.enrollEmployeeToPensionPlan(e3, plan3);

        LocalDate today = LocalDate.of(2026, 6, 1);
        // LocalDate today = LocalDate.now();
        LocalDate nextQuarterStart = getNextQuarterStart(today);
        LocalDate nextQuarterEnd = nextQuarterStart.plusMonths(3).minusDays(1);

        // Generate and print the Quarterly Upcoming Enrollees report using JSON format
        pensionPlanService.setReportStrategy(new JsonExport());
        System.out.println("All Employees Report:");
        System.out.println("--------------------------------------------------");
        var employees = pensionPlanService.getEmployees();
        System.out.println(pensionPlanService.generateReport(employees));
        System.out.println("Quarterly Upcoming Enrollees Report:");
        System.out.println("--------------------------------------------------");
        var employeesEligibleForPension = pensionPlanService.getQuarterlyUpcomingEnrollees(nextQuarterStart, nextQuarterEnd);
        System.out.println(pensionPlanService.generateReport(employeesEligibleForPension));
    }

    private static LocalDate getNextQuarterStart(LocalDate currentDate) {
        Month currentMonth = currentDate.getMonth();
        Month nextQuarterStartMonth;
        if (currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY || currentMonth == Month.MARCH) {
            nextQuarterStartMonth = Month.APRIL;
        } else if (currentMonth == Month.APRIL || currentMonth == Month.MAY || currentMonth == Month.JUNE) {
            nextQuarterStartMonth = Month.JULY;
        } else if (currentMonth == Month.JULY || currentMonth == Month.AUGUST || currentMonth == Month.SEPTEMBER) {
            nextQuarterStartMonth = Month.OCTOBER;
        } else {
            nextQuarterStartMonth = Month.JANUARY;
        }
        return LocalDate.of(currentDate.getYear(), nextQuarterStartMonth, 1);
    }
}