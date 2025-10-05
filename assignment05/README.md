# CS489 – Applied Software Development (ApSD)

## Assignment 05

This folder contains the database design artifacts, SQL script, and supporting materials for **Assignment 05** of **CS489 – Applied Software Development** at MIU.

---

## 📂 Repository Structure

- **ADS ERDDiagram.jpg** – Entity Relationship Diagram (ERD) for the ADS Dental Surgery database.
- **ADS UML.jpg** – UML Class Diagram illustrating the logical data model structure.
- **assignment 05.mdj** – StarUML project file containing both ERD and UML diagrams.
- **lab5a-relational-database-design-and-implementation.pdf** – Lab instructions and report document.
- **myADSDentalSurgeryDBScript.sql** – SQL Server script for creating and populating the ADS Dental Surgery database.
- **Sample-data.png** – Screenshot showing the populated sample data.
- **Show All Dentist Order By Lastname.png** – Query result showing all dentists ordered by last name.
- **Show All Appointments for a given Dentist by their dentist_Id.png** – Query result filtering appointments by dentist ID.
- **Show of the Appointments booked for a given Patient on a given Date.png** – Query result showing appointments for a specific patient on a given date.
- **Show All Appointments that have been scheduled at a Surgery Location.png** – Query result listing appointments by surgery location.

---

## 📝 Lab Task Overview

### Lab 5a – Relational Database Design & Implementation

1. **Database Design**

   - Designed an **Entity Relationship Diagram (ERD)** and a **UML Class Diagram** for the **ADS Dental Surgery System**.
   - Both diagrams are available as `.jpg` images and in the editable `.mdj` StarUML project file.

2. **Database Implementation**

   - Developed SQL Server script to:
     - Create normalized relational tables with primary/foreign key constraints.
     - Define appropriate data types, constraints, and relationships.
     - Populate tables with sample data for testing and demonstration.
   - Script file: `myADSDentalSurgeryDBScript.sql`

3. **Query Execution**
   - Implemented and tested key queries per lab requirements:
     - Display all dentists ordered by last name.
     - Show all appointments for a given dentist by `dentist_Id`.
     - Show all appointments scheduled at a specific surgery location.
     - Show all appointments booked for a given patient on a specific date.
   - Query results are saved as screenshots in the project folder.

---

## 🛠 Development Environment

- **Database System:** Microsoft SQL Server 2022
- **Database Management Tool:** SQL Server Management Studio (SSMS) / Azure Data Studio
- **Design Tool:** StarUML
- **File Formats:** `.sql`, `.jpg`, `.mdj`, `.pdf`
