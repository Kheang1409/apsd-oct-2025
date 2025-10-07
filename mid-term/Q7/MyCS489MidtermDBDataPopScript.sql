-- =============================================
-- Royal Windsor Realty - Leasing Management
-- Microsoft SQL Server Script
-- =============================================

-- Create Database
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'RWRLeasingDB')
BEGIN
    CREATE DATABASE RWRLeasingDB;
END
GO

USE RWRLeasingDB;
GO

-- =============================================
-- Drop tables if they exist (for reruns)
-- =============================================
DROP TABLE IF EXISTS Leases;
DROP TABLE IF EXISTS Tenants;
DROP TABLE IF EXISTS Apartments;
GO

-- =============================================
-- 1. Apartments (include Address)
-- =============================================
CREATE TABLE Apartments (
    Apartment_Id INT IDENTITY(1,1) PRIMARY KEY,
    Apartment_Number VARCHAR(50) NOT NULL,
    Property_Name VARCHAR(50) NOT NULL,
    FloorNo INT NULL,
    Size DECIMAL(9,2),
    Number_Of_Rooms INT,
    Street VARCHAR(100) NOT NULL,
    City VARCHAR(50) NOT NULL,
    State CHAR(2) NOT NULL,
    ZipCode VARCHAR(10) NOT NULL
);
GO

-- =============================================
-- 2. Tenants
-- =============================================
CREATE TABLE Tenants (
    Tenant_Id INT IDENTITY(1,1) PRIMARY KEY,
    First_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    PhoneNumber VARCHAR(50) NOT NULL,
    Email VARCHAR(50) NULL UNIQUE
);
GO

-- =============================================
-- 3. Leases
-- =============================================
CREATE TABLE Leases (
    Lease_Id INT IDENTITY(1,1) PRIMARY KEY,
    Lease_Number VARCHAR(50) NOT NULL UNIQUE,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    MonthlyRentalRate DECIMAL(9,2) NOT NULL,
    Tenant_Id INT NOT NULL,
    Apartment_Id INT NOT NULL,
    FOREIGN KEY (Tenant_Id) REFERENCES Tenants(Tenant_Id),
    FOREIGN KEY (Apartment_Id) REFERENCES Apartments(Apartment_Id)
);
GO

-- =============================================
-- SAMPLE DML INSERTS
-- =============================================

-- Apartments (with address)
INSERT INTO Apartments (Apartment_Number, Property_Name, FloorNo, Size, Number_Of_Rooms, Street, City, State, ZipCode) VALUES
('K1210', 'Bells Court', 12, 1150.00, 2, '123 West Avenue', 'Phoenix', 'AZ', '85012'),
('B1109', 'The Galleria', 11, 970.00, 1, '900 Johns Street', 'Cleveland', 'OH', '43098'),
('G815', 'Bells Court', 8, 1150.00, 2, '123 West Avenue', 'Phoenix', 'AZ', '85012');
GO

-- Tenants
INSERT INTO Tenants (First_Name, Last_Name, PhoneNumber, Email) VALUES
('Robert', 'Lanskov', '(480) 123-1355', NULL),
('Anna', 'Smith', '(414) 998-0112', 'asmith@mail.com');
GO

-- Leases
INSERT INTO Leases (Lease_Number, StartDate, EndDate, MonthlyRentalRate, Tenant_Id, Apartment_Id) VALUES
('D0187-175', '2021-10-01', '2022-09-30', 1750.00, 1, 1),
('W1736-142', '2022-08-15', '2024-02-14', 1500.00, 2, 2),
('DD001-142', '2022-10-01', '2023-09-30', 1975.00, 1, 1),
('P162-0017', '2023-10-01', '2024-09-30', 2275.00, 1, 1);
GO