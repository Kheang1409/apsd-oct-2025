# Quarterly Upcoming Enrollees Report - Implementation Summary

## Overview

This document describes the implementation of the **Quarterly Upcoming Enrollees Report** feature for the Employee Pension Plan CLI application. This report helps HR managers identify employees who will be eligible for pension plan enrollment in the next quarter.

## Business Requirements

The Quarterly Upcoming Enrollees report should include:

- Employees whose period of employment has been **at least 3 years** on or between the first and last day of the next quarter
- Employees who have **NOT yet been enrolled** to a Pension Plan
- Results sorted by employment date in descending order (most recent first)

## Implementation Details

### 1. Core Logic Changes

#### Employee.java - Eligibility Check

```java
@JsonIgnore
public boolean isEligibleForPension(LocalDate nextQuarterStart, LocalDate nextQuarterEnd) {
    // Check if employee will have at least 3 years of employment
    // at any point during the next quarter (between start and end dates)
    long yearsOfEmploymentAtEndOfNextQuarter =
        ChronoUnit.YEARS.between(employmentDate, nextQuarterEnd);
    return yearsOfEmploymentAtEndOfNextQuarter >= 3;
}
```

**Key Implementation Decision**: The method checks eligibility against the **end of the quarter** to ensure that employees who will reach 3 years at any point during the quarter are included.

#### PensionPlanService.java - Report Generation

```java
@Override
public List<Employee> getQuarterlyUpcomingEnrollees(LocalDate nextQuarterStart, LocalDate nextQuarterEnd) {
   return employeeDAO.getAllEmployees().stream()
        .filter(e -> !e.isEnrolled() && e.isEligibleForPension(nextQuarterStart, nextQuarterEnd))
        .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
        .collect(Collectors.toList());
}
```

**Filter Logic**:

1. `!e.isEnrolled()` - Excludes employees already enrolled in pension plans
2. `e.isEligibleForPension(nextQuarterStart, nextQuarterEnd)` - Includes employees with 3+ years of service
3. `sorted(...)` - Orders by employment date (descending)

### 2. Quarter Calculation Fix

#### Main.java - Next Quarter Calculation

```java
private static LocalDate getNextQuarterStart(LocalDate currentDate) {
    Month currentMonth = currentDate.getMonth();
    Month nextQuarterStartMonth;
    int year = currentDate.getYear();

    if (currentMonth == Month.JANUARY || currentMonth == Month.FEBRUARY || currentMonth == Month.MARCH) {
        nextQuarterStartMonth = Month.APRIL;
    } else if (currentMonth == Month.APRIL || currentMonth == Month.MAY || currentMonth == Month.JUNE) {
        nextQuarterStartMonth = Month.JULY;
    } else if (currentMonth == Month.JULY || currentMonth == Month.AUGUST || currentMonth == Month.SEPTEMBER) {
        nextQuarterStartMonth = Month.OCTOBER;
    } else {
        // For October, November, December - next quarter is January of next year
        nextQuarterStartMonth = Month.JANUARY;
        year++;  // Important: Increment year for Q4 -> Q1 transition
    }
    return LocalDate.of(year, nextQuarterStartMonth, 1);
}
```

**Bug Fix**: Added proper year increment when transitioning from Q4 to Q1 of the following year.

## Test Results

### Sample Data Analysis (as of October 2, 2025)

- **Next Quarter**: Q1 2026 (January 1 - March 31, 2026)

#### Eligible Employees:

1. **Anna Wiltord** (employed March 15, 2023)

   - Employment duration by March 31, 2026: ~3 years ✓
   - Not enrolled in pension plan ✓

2. **Daniel Agar** (employed January 17, 2023)
   - Employment duration by March 31, 2026: ~3.2 years ✓
   - Not enrolled in pension plan ✓

#### Excluded Employees:

- **Benard Shaw** & **Carly Agar**: Already enrolled in pension plans
- **Wesley Schneider** (July 21, 2023): ~2.7 years by March 31, 2026 (< 3 years)
- **Yosef Tesfalem** (October 31, 2024): ~1.4 years by March 31, 2026 (< 3 years)

### Unit Tests

- ✅ `testQuarterlyUpcomingEnrollees()`: Verifies filtering logic
- ✅ `testEmployeeEligibilityForPension()`: Verifies eligibility calculations

## Application Output

```json
Quarterly Upcoming Enrollees Report (2026-01-01 to 2026-03-31):
--------------------------------------------------
[ {
  "id" : 5,
  "employmentDate" : "2023-03-15",
  "salary" : 85750.0,
  "name" : "Anna Wiltord"
}, {
  "id" : 1,
  "employmentDate" : "2023-01-17",
  "salary" : 105945.5,
  "name" : "Daniel Agar"
} ]
```

## Project Structure

```
src/main/java/apsd/lab2a/
├── Main.java                    # Application entry point with quarter calculation
├── domain/
│   ├── Employee.java           # Enhanced with pension eligibility logic
│   └── PensionPlan.java        # Existing pension plan domain
├── service/
│   ├── IPensionPlanService.java # Interface with new method
│   └── PensionPlanService.java  # Implementation with report generation
├── dao/
│   ├── EmployeeDAO.java        # Data access interface
│   └── InMemoryEmployeeDAO.java # In-memory implementation
└── strategy/
    ├── IReportStrategy.java    # Report generation strategy interface
    └── JsonExport.java         # JSON format implementation

src/test/java/apsd/lab2a/
└── service/
    └── PensionPlanServiceTest.java # Unit tests for new functionality
```

## Key Features

1. **Accurate Date Calculations**: Properly handles year transitions and quarter boundaries
2. **Flexible Reporting**: Uses Strategy pattern for different output formats
3. **Comprehensive Filtering**: Considers both enrollment status and employment duration
4. **Sorted Results**: Orders by employment date for better usability
5. **Well-Tested**: Unit tests verify core functionality

## Technologies Used

- **Java 22**: Core application development
- **Jackson**: JSON serialization/deserialization
- **JUnit 5**: Unit testing framework
- **Maven**: Build and dependency management

This implementation provides HR managers with a reliable tool to identify employees who will become eligible for pension plan enrollment in the upcoming quarter, supporting efficient workforce management and benefits administration.
