# CS489 – Applied Software Development (ApSD)

## Lab 2a & 2b: Employee Pension Management System and Patient Appointment Management System

This repository contains the source code, project files, and supporting materials for **Lab 2a** and **Lab 2b** of **CS489 – Applied Software Development** at MIU.

### Lab 2a (October 2025) – Employee Pension Management System (CLI App)

In **Lab 2a**, a Command-Line Interface (CLI) application was developed for a company’s Employee Pension Planning System. The system allows HR managers to:

- Enroll new Employees in Pension Plans.
- Maintain Employee and Pension Plan data.
- Generate a report of Employees eligible for Pension Plan enrollment in the upcoming quarter.

### Lab 2b (October 2025) – Patient Appointment Management System (CLI App)

In **Lab 2b**, a Command-Line Interface (CLI) application was developed for a hospital's Patient Appointment Management System (PAMS). The system allows:

- Registration of new Patients with personal information.
- Sorting Patients by their age.
- Outputting the data in JSON format for record-keeping.

---

## 📂 Repository Structure

- **lab2a/**

  - **src/** – Source code for Employee Pension Management System (Lab 2a).

- **lab2b/**

  - **src/** – Source code for Patient Appointment Management System (Lab 2b).

- **screenshots/** – Screenshots demonstrating successful execution of tasks in Lab 2a.

---

## 🛠 Development Environment

- **IDE/Editor:** IntelliJ IDEA / Visual Studio Code (or preferred code editor)
- **Languages:** Java 22
- **Build Tools:** Apache Maven (or Gradle for optional version)
- **CI/CD Tool:** GitHub Actions for automated build and deployment
- **Version Control:** Git/GitHub

---

## 🚀 How to Run

1. Clone this repository:

   ```bash
   git clone https://github.com/yourusername/CS489-Lab-2.git
   ```

2. **For Lab 2a (Employee Pension Management System)**:

   - Open the `lab2a/src/` folder in your IDE.
   - Build the Maven project and run the CLI application for managing Employees and Pension Plans.
   - Check the `lab2a/screenshots/` folder for proof of successful execution.

3. **For Lab 2b (Patient Appointment Management System)**:

   - Open the `lab2b/src/` folder in your IDE.
   - Build the Maven project and run the CLI application to manage Patients and their appointments.
   - The JSON output of the sorted Patient data will be generated in the local filesystem.
   - Check the `lab2b/screenshots/` folder for proof of successful execution.

---

## 📝 Features Implemented

### Lab 2a: Employee Pension Management System (CLI)

1. **Employee Class**: Represents employee data including ID, first name, last name, employment date, and yearly salary.
2. **PensionPlan Class**: Represents pension plan data including plan reference number, enrollment date, and monthly contribution.
3. **Quarterly Upcoming Enrollees Report**: Lists employees who qualify for pension enrollment in the next quarter.
4. **Data Sorting**: Employees are listed by descending salary and ascending last name.
5. **CI/CD Pipeline**: Automated build process on every GitHub commit or pull request using GitHub Actions.

### Lab 2b: Patient Appointment Management System (CLI)

1. **Patient Class**: Represents patient data including ID, first name, last name, phone number, email, mailing address, and date of birth.
2. **JSON Output**: Patient data is sorted by age in descending order and output to a JSON file.
3. **CI/CD Pipeline**: Automated build process on every GitHub commit or pull request using GitHub Actions.

---

## 📌 Notes

- All screenshots serve as evidence of the correct execution and successful implementation of the required features for each lab.
- The automated CI/CD pipeline ensures that the project is built and tested every time code is pushed to GitHub.
- Both applications are built using Maven for project management and dependencies.

---

✅ **Lab 2a & Lab 2b submitted (October 2025).**
