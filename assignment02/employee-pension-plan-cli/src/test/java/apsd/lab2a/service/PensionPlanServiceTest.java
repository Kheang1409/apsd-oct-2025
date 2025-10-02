package apsd.lab2a.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import apsd.lab2a.dao.InMemoryEmployeeDAO;
import apsd.lab2a.domain.Employee;
import apsd.lab2a.domain.PensionPlan;

public class PensionPlanServiceTest {
    
    private PensionPlanService pensionPlanService;
    
    @BeforeEach
    void setUp() {
        pensionPlanService = new PensionPlanService(new InMemoryEmployeeDAO());
    }
    
    @Test
    void testQuarterlyUpcomingEnrollees() {
        // Create test employees
        Employee eligible1 = new Employee(1, "John", "Doe", LocalDate.of(2020, 1, 1), 50000.0);
        Employee eligible2 = new Employee(2, "Jane", "Smith", LocalDate.of(2021, 6, 15), 60000.0);
        Employee notEligibleYet = new Employee(3, "Bob", "Johnson", LocalDate.of(2023, 12, 1), 55000.0);
        Employee alreadyEnrolled = new Employee(4, "Alice", "Brown", LocalDate.of(2019, 3, 10), 65000.0);
        
        // Enroll Alice to a pension plan
        PensionPlan plan = new PensionPlan("PL001", LocalDate.of(2022, 1, 1), 500.0, alreadyEnrolled);
        alreadyEnrolled.setPensionPlan(plan);
        
        // Save employees
        pensionPlanService.saveEmployee(eligible1);
        pensionPlanService.saveEmployee(eligible2);
        pensionPlanService.saveEmployee(notEligibleYet);
        pensionPlanService.saveEmployee(alreadyEnrolled);
        
        // Test for Q1 2025 (Jan 1 - Mar 31, 2025)
        LocalDate quarterStart = LocalDate.of(2025, 1, 1);
        LocalDate quarterEnd = LocalDate.of(2025, 3, 31);
        
        List<Employee> upcomingEnrollees = pensionPlanService.getQuarterlyUpcomingEnrollees(quarterStart, quarterEnd);
        
        // Should include eligible1 and eligible2, but not notEligibleYet (less than 3 years) 
        // and not alreadyEnrolled (already has pension plan)
        assertEquals(2, upcomingEnrollees.size());
        assertTrue(upcomingEnrollees.stream().anyMatch(e -> e.getId() == 1)); // John Doe
        assertTrue(upcomingEnrollees.stream().anyMatch(e -> e.getId() == 2)); // Jane Smith
        assertFalse(upcomingEnrollees.stream().anyMatch(e -> e.getId() == 3)); // Bob Johnson (not eligible yet)
        assertFalse(upcomingEnrollees.stream().anyMatch(e -> e.getId() == 4)); // Alice Brown (already enrolled)
    }
    
    @Test
    void testEmployeeEligibilityForPension() {
        Employee employee = new Employee(1, "Test", "Employee", LocalDate.of(2020, 6, 1), 50000.0);
        
        // Test for Q2 2024 (Apr 1 - Jun 30, 2024) - should be eligible (4+ years)
        assertTrue(employee.isEligibleForPension(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 6, 30)));
        
        // Test for Q1 2023 (Jan 1 - Mar 31, 2023) - should NOT be eligible (less than 3 years)
        assertFalse(employee.isEligibleForPension(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 3, 31)));
        
        // Test for Q2 2023 (Apr 1 - Jun 30, 2023) - should be eligible (exactly 3 years by June 30)
        assertTrue(employee.isEligibleForPension(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 6, 30)));
        
        // Test edge case: employee not quite 3 years
        Employee notQuite3Years = new Employee(2, "Not", "Quite", LocalDate.of(2020, 7, 1), 50000.0);
        assertFalse(notQuite3Years.isEligibleForPension(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 6, 30)));
    }
}