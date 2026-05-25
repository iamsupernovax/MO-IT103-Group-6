MO-IT103L - Group 6
Section: H1101

Members:
John Linnard Dumdum (lr.jldumdum@mmdc.mcl.edu.ph)
Arlynne Alvarez (lr.aalvarez@mmdc.mcl.edu.ph)
Katrina Liporada (lr.kliporada@mmdc.mcl.edu.ph)
Rizalyn Novales (lr.rnovales@mmdc.mcl.edu.ph)
Rose Ann Soriano (lr.rasoriano@mmdc.mcl.edu.ph)





# MotorPH Employee App

A Java Swing GUI application for managing MotorPH employee information and payroll.
Implements **MPHCR01 - Feature 1** of the MO-IT103 Computer Programming 2 change requests.

## Features
- Login screen with username and password
- Employee form with fields for Employee Number, Employee Name, Month, and Year
- Either-or input rule (Employee Number or Name is enough)
- Input validation with try/catch error handling
- Cross-reference with CSV employee data
- Computed payroll summary for the selected month and year
- Colored buttons that work consistently on Windows, macOS, and Linux

## Project Structure
- `JavaApplication12/src/motorphpayrollsystem/MotorPHPayrollGUI.java` - the GUI windows
- `JavaApplication12/src/motorphpayrollsystem/MotorPHPayrollSystem.java` - the backend logic (CSV loading, payroll math)
- `JavaApplication12/src/motorphpayrollsystem/MotorPH_Employee Data - Employee Details.csv` - employee records
- `JavaApplication12/src/motorphpayrollsystem/MotorPH_Employee Data - Attendance Record.csv` - attendance records

## How to Run
1. Open the `JavaApplication12` project in NetBeans.
2. Right-click the project then choose **Clean and Build**.
3. Right-click then choose **Run** (or press F6).
4. Log in with username `employee` or `payroll_staff` and password `12345`.


