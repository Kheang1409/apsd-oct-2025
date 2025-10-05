-- Drop and recreate database (optional for re-runs)
IF DB_ID('ADS_DB') IS NOT NULL
    DROP DATABASE ADS_DB;
GO

CREATE DATABASE ADS_DB;
GO

USE ADS_DB;
GO

----------------------------------------------------------------------------------
-- Table: Users
----------------------------------------------------------------------------------
CREATE TABLE Users (
    Id INT PRIMARY KEY,
    Username VARCHAR(25) NOT NULL UNIQUE,
    Password VARCHAR(10) NOT NULL,
    Role VARCHAR(10) NOT NULL -- Assuming Role could be 'Admin', 'Dentist', 'Patient', etc.
);
GO

----------------------------------------------------------------------------------
-- Table: Patients
----------------------------------------------------------------------------------
CREATE TABLE Patients (
    Id INT PRIMARY KEY,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    Contact VARCHAR(20),
    DateOfBirth DATE,
    Street VARCHAR(50),
    City VARCHAR(50),
    State VARCHAR(2),
    ZipCode VARCHAR(5),
    User_Id INTEGER NOT NULL,
    -- Foreign Key Constraint to link a Patient to a User account
    FOREIGN KEY (User_Id) REFERENCES Users(Id)
);
GO

----------------------------------------------------------------------------------
-- Table: Dentists
----------------------------------------------------------------------------------
CREATE TABLE Dentists (
    Id INT PRIMARY KEY,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    Contact VARCHAR(20),
    Specialization VARCHAR(255), -- Increased size for potential longer specialization names
    User_Id INTEGER NOT NULL,
    -- Foreign Key Constraint to link a Dentist to a User account
    FOREIGN KEY (User_Id) REFERENCES Users(Id)
);
GO

----------------------------------------------------------------------------------
-- Table: Services
----------------------------------------------------------------------------------
CREATE TABLE Services (
    Id INT PRIMARY KEY,
    Name VARCHAR(150) NOT NULL UNIQUE
);
GO

----------------------------------------------------------------------------------
-- Table: Surgeries
----------------------------------------------------------------------------------
CREATE TABLE Surgeries (
    Id INT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL UNIQUE,
    TelephoneNumber VARCHAR(20),
    Street VARCHAR(50),
    City VARCHAR(50),
    State VARCHAR(2),
    ZipCode VARCHAR(5)
);
GO

----------------------------------------------------------------------------------
-- Table: Appointments
----------------------------------------------------------------------------------
CREATE TABLE Appointments (
    Id INT PRIMARY KEY,
    Patient_Id INTEGER NOT NULL,
    Dentist_Id INTEGER NOT NULL,
    Service_Id INTEGER NOT NULL,
    Surgery_Id INTEGER, -- Assuming surgery is optional for an appointment
    Date DATE NOT NULL,
    Street VARCHAR(50),
    City VARCHAR(50),
    State VARCHAR(2),
    ZipCode VARCHAR(5),
    Status VARCHAR(10), -- e.g., 'Scheduled', 'Completed', 'Cancelled'
    
    -- Foreign Key Constraints
    FOREIGN KEY (Patient_Id) REFERENCES Patients(Id),
    FOREIGN KEY (Dentist_Id) REFERENCES Dentists(Id),
    FOREIGN KEY (Service_Id) REFERENCES Services(Id),
    FOREIGN KEY (Surgery_Id) REFERENCES Surgeries(Id)
);
GO

----------------------------------------------------------------------------------
-- Table: Bills
----------------------------------------------------------------------------------
CREATE TABLE Bills (
    Id INT PRIMARY KEY,
    Patient_Id INTEGER NOT NULL,
    Appointment_Id INTEGER NOT NULL UNIQUE, -- Assuming a bill is tied to a unique appointment
    Amount DECIMAL(10, 2) NOT NULL, -- DECIMAL(10, 2) is a standard type for currency
    Status VARCHAR(10) NOT NULL, -- e.g., 'Paid', 'Pending', 'Overdue'
    
    -- Foreign Key Constraints
    FOREIGN KEY (Patient_Id) REFERENCES Patients(Id),
    FOREIGN KEY (Appointment_Id) REFERENCES Appointments(Id)
);
GO
