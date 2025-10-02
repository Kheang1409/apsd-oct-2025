package apsd.lab2a.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate employmentDate;
    private double salary;
    
    @JsonManagedReference
    private PensionPlan pensionPlan;

    public Employee() {
    }

    public Employee(long id, String firstName, String lastName, LocalDate employmentDate, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employmentDate = employmentDate;
        this.salary = salary;
    }

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("name")  // Custom getter for the "name" field
    public String getName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("employmentDate")
    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @JsonProperty("salary")
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @JsonProperty("pensionPlan")  // Include pensionPlan as part of the Employee object
    public PensionPlan getPensionPlan() {
        return pensionPlan;
    }

    public void setPensionPlan(PensionPlan pensionPlan) {
        this.pensionPlan = pensionPlan;
    }

    @JsonIgnore
    public boolean isEligibleForPension(LocalDate nextQuarterStart, LocalDate nextQuarterEnd) {
        // Check if employee will have at least 3 years of employment 
        // at any point during the next quarter (between start and end dates)
        long yearsOfEmploymentAtEndOfNextQuarter =
            ChronoUnit.YEARS.between(employmentDate, nextQuarterEnd);
        return yearsOfEmploymentAtEndOfNextQuarter >= 3;
    }

    @JsonIgnore
    public boolean isEnrolled() {
        return pensionPlan != null;
    }
}