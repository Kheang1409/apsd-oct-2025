package apsd.lab2a.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class PensionPlan {

    private String planReferenceNumber;
    private LocalDate enrollmentDate;
    private double monthlyContribution;
    @JsonBackReference 
    private Employee employee;
    
    public PensionPlan(String planReferenceNumber, LocalDate enrollmentDate, double monthlyContribution, Employee employee) {
        this.planReferenceNumber = planReferenceNumber;
        this.enrollmentDate = enrollmentDate;
        this.monthlyContribution = monthlyContribution;
        this.employee = employee;
    }
    public String getPlanReferenceNumber() {
        return planReferenceNumber;
    }
    public void setPlanReferenceNumber(String planReferenceNumber) {
        this.planReferenceNumber = planReferenceNumber;
    }
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
    public double getMonthlyContribution() {
        return monthlyContribution;
    }
    public void setMonthlyContribution(double monthlyContribution) {
        this.monthlyContribution = monthlyContribution;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
