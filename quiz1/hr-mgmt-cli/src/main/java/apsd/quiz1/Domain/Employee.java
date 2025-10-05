package apsd.quiz1.Domain;

import java.time.LocalDate;

public class Employee {
    private String employeeNo;
    private String firstName;
    private String lastName;
    private LocalDate dateOfDate;
    private LocalDate dateOfEmployment;
    private double biweeklySalary;

    private Employee(
        String employeeNo,
        String firstName,
        String lastName,
        LocalDate dateOfDate,
        LocalDate dateOfEmployment,
        double biweeklySalary
    ){
        this.employeeNo = employeeNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfDate = dateOfDate;
        this.dateOfEmployment = dateOfEmployment;
        this.biweeklySalary = biweeklySalary;
    }

    public static class Builder{
        private String employeeNo;
        private String firstName;
        private String lastName;
        private LocalDate dateOfDate;
        private LocalDate dateOfEmployment;
        private double biweeklySalary;


        public Builder employeeNo(String employeeNo){
            this.employeeNo = employeeNo;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder dateOfDate(LocalDate dateOfDate){
            this.dateOfDate = dateOfDate;
            return this;
        }

        public Builder dateOfEmployment(LocalDate dateOfEmployment){
            this.dateOfEmployment = dateOfEmployment;
            return this;
        }

        public Builder biweeklySalary(double biweeklySalary){
            this.biweeklySalary = biweeklySalary;
            return this;
        }

        public Employee build(){
            return new Employee(employeeNo, firstName, lastName, dateOfDate, dateOfEmployment, biweeklySalary);
        }

        public static Builder builder(){
            return new Builder();
        }
    }

    public String getEmployeeNo() {
        return employeeNo;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfDate() {
        return dateOfDate;
    }
    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }
    public double getBiweeklySalary() {
        return biweeklySalary;
    }
    public int getYearsOfEmployment() {
        if (dateOfEmployment == null) return 0;
        int years = LocalDate.now().getYear() - dateOfEmployment.getYear();
        if (years < 0) years = 0;
        return years;
    }
}
