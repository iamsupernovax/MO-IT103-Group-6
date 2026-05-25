/*
 * MotorPH Employee App - Graphical User Interface
 * Change Request: MPHCR01 - Feature 1 (GUI Implementation)
 *
 */
package motorphpayrollsystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

public class MotorPHPayrollGUI {

    static JFrame frame;
    static JTextArea outputArea;

    static Map<String, String[]> employees;
    static List<String[]> attendance;

    // ================= COLORS =================
    static final Color BACKGROUND = new Color(245, 247, 250);
    static final Color BLUE       = new Color(41, 128, 185);
    static final Color GREEN      = new Color(39, 174, 96);
    static final Color RED        = new Color(192, 57, 43);
    static final Color DARK       = new Color(44, 62, 80);

    // ================= FONTS =================
    static final Font TITLE_FONT  = new Font("Segoe UI", Font.BOLD, 26);
    static final Font LABEL_FONT  = new Font("Segoe UI", Font.BOLD, 14);
    static final Font FIELD_FONT  = new Font("Segoe UI", Font.PLAIN, 14);
    static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    static final Font RESULT_FONT = new Font("Monospaced", Font.PLAIN, 14);

    // =================================================
    // MAIN
    // =================================================

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ex) {
            // If the style change fails, just keep the default look.
        }

        // Read the two CSV files into memory
        employees  = MotorPHPayrollSystem.loadEmployees(
                MotorPHPayrollSystem.EMPLOYEE_CSV);
        attendance = MotorPHPayrollSystem.loadAttendance(
                MotorPHPayrollSystem.ATTENDANCE_CSV);

        // Open the first window
        createLoginGUI();
    }

    // =================================================
    // LOGIN SCREEN
    // =================================================

    static void createLoginGUI() {

        frame = new JFrame("MotorPH Employee App - Login");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("MotorPH Employee App");
        title.setFont(TITLE_FONT);
        title.setForeground(DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(40, 0, 20, 0));
        frame.add(title, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(BACKGROUND);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20, 170, 50, 170));

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(LABEL_FONT);
        userLabel.setForeground(DARK);

        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        usernameField.setFont(FIELD_FONT);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(LABEL_FONT);
        passLabel.setForeground(DARK);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        passwordField.setFont(FIELD_FONT);

        JButton loginButton = new JButton("Login");
        styleButton(loginButton, BLUE);

        centerPanel.add(userLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        centerPanel.add(usernameField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(passLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        centerPanel.add(loginButton);

        frame.add(centerPanel, BorderLayout.CENTER);

        // What happens when the user clicks the Login button
        loginButton.addActionListener(e -> {

            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            // Both boxes must be filled in
            if (username.isEmpty() || password.isEmpty()) {
                showError("Please complete all fields.");
                return;
            }

            // Credentials check
            boolean validUser =
                    username.equals("employee")
                    || username.equals("payroll_staff");

            if (validUser && password.equals("12345")) {
                frame.dispose();
                createFeature1FormGUI();   // proceed to Feature 1 form
            } else {
                showError("Invalid username or password.");
            }
        });

        frame.setVisible(true);
    }

    // =================================================
    // FEATURE 1 - EMPLOYEE INPUT FORM
    // (Employee Number + Employee Name + Pay Coverage)
    // =================================================

    static void createFeature1FormGUI() {

        frame = new JFrame("MotorPH Employee App - Employee Form");
        frame.setSize(620, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Employee Information");
        title.setFont(TITLE_FONT);
        title.setForeground(DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(25, 0, 15, 0));
        frame.add(title, BorderLayout.NORTH);

        // Make a panel that arranges items in 5 rows and 2 columns.
        // The two 10s are the spacing between cells.
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBackground(BACKGROUND);
        formPanel.setBorder(new EmptyBorder(10, 60, 10, 60));

        // Row 1: Employee Number label and text box
        JLabel empNoLabel = new JLabel("Employee Number:");
        empNoLabel.setFont(LABEL_FONT);
        empNoLabel.setForeground(DARK);

        JTextField empNoField = new JTextField();
        empNoField.setFont(FIELD_FONT);

        formPanel.add(empNoLabel);
        formPanel.add(empNoField);

        // Row 2: Employee Name label and text box
        JLabel empNameLabel = new JLabel("Employee Name:");
        empNameLabel.setFont(LABEL_FONT);
        empNameLabel.setForeground(DARK);

        JTextField empNameField = new JTextField();
        empNameField.setFont(FIELD_FONT);

        formPanel.add(empNameLabel);
        formPanel.add(empNameField);

        // Row 3: "Pay Coverage" header on the left, empty cell on the right
        JLabel payHeader = new JLabel("Pay Coverage");
        payHeader.setFont(new Font("Segoe UI", Font.BOLD, 15));
        payHeader.setForeground(BLUE);

        formPanel.add(payHeader);
        formPanel.add(new JLabel(""));

        // Row 4: Month label and dropdown list
        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setFont(LABEL_FONT);
        monthLabel.setForeground(DARK);

        String[] months = {
                "January", "February", "March", "April",
                "May", "June", "July", "August",
                "September", "October", "November", "December"
        };
        JComboBox<String> monthBox = new JComboBox<>(months);
        monthBox.setFont(FIELD_FONT);

        formPanel.add(monthLabel);
        formPanel.add(monthBox);

        // Row 5: Year label and dropdown list
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(LABEL_FONT);
        yearLabel.setForeground(DARK);

        // Years the user can pick from. Add more if needed.
        String[] years = { "2024", "2025", "2026" };
        JComboBox<String> yearBox = new JComboBox<>(years);
        yearBox.setFont(FIELD_FONT);

        formPanel.add(yearLabel);
        formPanel.add(yearBox);

        frame.add(formPanel, BorderLayout.CENTER);

        // --- Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(BACKGROUND);

        JButton submitButton = new JButton("Submit");
        styleButton(submitButton, GREEN);
        submitButton.setPreferredSize(new Dimension(140, 40));

        JButton clearButton = new JButton("Clear");
        styleButton(clearButton, BLUE);
        clearButton.setPreferredSize(new Dimension(140, 40));

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, RED);
        exitButton.setPreferredSize(new Dimension(140, 40));

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // What each button does when clicked

        // Submit: check the inputs, then show the result window
        submitButton.addActionListener(e -> handleSubmit(
                empNoField,
                empNameField,
                monthBox,
                yearBox));

        // Clear: empty all the boxes
        clearButton.addActionListener(e -> {
            empNoField.setText("");
            empNameField.setText("");
            monthBox.setSelectedIndex(0);
            yearBox.setSelectedIndex(0);
        });

        // Exit: close the whole program
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // =================================================
    // Runs when the Submit button is clicked.
    // Checks the inputs, then shows the result window.
    // =================================================

    static void handleSubmit(
            JTextField empNoField,
            JTextField empNameField,
            JComboBox<String> monthBox,
            JComboBox<String> yearBox) {

        // ---- Raw values ----
        String empNoText = empNoField.getText().trim();
        String empName   = empNameField.getText().trim();
        String monthName = (String) monthBox.getSelectedItem();
        String yearText  = (String) yearBox.getSelectedItem();

        // ---- "Either-or" required field check ----
        // Submission is allowed when at least one of Employee Number or
        // Employee Name is filled in. The other can be left blank.
        if (empNoText.isEmpty() && empName.isEmpty()) {
            showError("Please fill in at least one of "
                    + "Employee Number or Employee Name.");
            return;
        }

        // If the user typed an Employee Number, make sure it is a
        // positive whole number. We use try/catch to handle the case
        // where the user typed letters by mistake.
        boolean empNoProvided = false;  // becomes true if a valid number is given
        int empNoInt = 0;
        if (!empNoText.isEmpty()) {
            try {
                empNoInt = Integer.parseInt(empNoText);
                if (empNoInt <= 0) {
                    showError("Employee Number must be a positive "
                            + "whole number.");
                    return;
                }
                empNoProvided = true;
            } catch (NumberFormatException ex) {
                showError("Employee Number must contain digits only.\n"
                        + "You entered: \"" + empNoText + "\"");
                return;
            }
        }

        // If the user typed a name, make sure it has only letters,
        // spaces, and simple punctuation (period, comma, apostrophe, hyphen).
        if (!empName.isEmpty() && !isValidName(empName)) {
            showError("Employee Name should contain only letters "
                    + "and spaces.");
            return;
        }

        // The Year always comes from a dropdown, but we still use
        // try/catch just in case something weird happens.
        int yearInt;
        try {
            yearInt = Integer.parseInt(yearText);
        } catch (NumberFormatException ex) {
            showError("Please select a valid year.");
            return;
        }

        // Translate month name to month number (1-12)
        int monthInt = monthNameToNumber(monthName);

        // ---- Cross-reference with CSV (by Employee Number OR Name) ----
        String csvEmpKey = "";
        String[] csvEmp = null;

        // First, try lookup by Employee Number
        if (empNoProvided && employees != null) {
            csvEmpKey = String.valueOf(empNoInt);
            csvEmp = employees.get(csvEmpKey);
        }

        // If no match by number and name was provided, try lookup by name
        if (csvEmp == null && !empName.isEmpty() && employees != null) {
            int matchCount = countEmployeeNameMatches(empName);
            if (matchCount > 1) {
                showError("Multiple employees match the name \"" + empName + "\".\n"
                        + "Please also enter the Employee Number to identify which one.");
                return;
            } else if (matchCount == 1) {
                csvEmp = searchEmployeeByName(empName);
                if (csvEmp != null) {
                    csvEmpKey = csvEmp[0];
                }
            }
        }

        String csvMatchStatus;
        if (csvEmp != null) {
            csvMatchStatus = "MATCH FOUND in CSV: "
                    + csvEmp[1] + ", " + csvEmp[2]
                    + " (Position: " + csvEmp[4] + ")";
        } else if (empNoProvided) {
            csvMatchStatus = "No matching record found in CSV "
                    + "for Employee Number " + empNoInt + ".";
        } else if (!empName.isEmpty()) {
            csvMatchStatus = "No matching record found in CSV "
                    + "for Employee Name \"" + empName + "\".";
        } else {
            csvMatchStatus = "Skipped (Employee Number was not provided).";
        }

        // Build the result text using plain String concatenation
        String empNoOut   = empNoProvided ? String.valueOf(empNoInt) : "(not provided)";
        String empNameOut = empName.isEmpty() ? "(not provided)" : empName;

        String result = "";
        result += "========================================\n";
        result += "       SUBMITTED EMPLOYEE INFORMATION   \n";
        result += "========================================\n\n";
        result += "Employee Number : " + empNoOut + "\n";
        result += "Employee Name   : " + empNameOut + "\n";
        result += "\n----------------------------------------\n";
        result += "Pay Coverage\n";
        result += "----------------------------------------\n";
        result += "Period          : " + monthName + " " + yearInt + "\n";
        result += "\n----------------------------------------\n";
        result += "CSV Cross-reference\n";
        result += "----------------------------------------\n";
        result += csvMatchStatus + "\n";

        // If CSV record exists, additionally show computed payroll for the
        // chosen month and year. The math itself is in MotorPHPayrollSystem.
        if (csvEmp != null) {
            result += "\n----------------------------------------\n";
            result += "Computed Payroll for " + monthName + " " + yearInt + "\n";
            result += "----------------------------------------\n";
            result += buildPayrollSummary(csvEmpKey, csvEmp, monthInt, yearInt);
        }

        showResultScreen(result);
    }

    // Returns true if the given name contains only letters, spaces,
    // and common punctuation (.,'-). This avoids using regex.
    static boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            boolean ok =
                    (c >= 'A' && c <= 'Z')
                    || (c >= 'a' && c <= 'z')
                    || c == ' '
                    || c == '.'
                    || c == ','
                    || c == '\''
                    || c == '-';
            if (!ok) {
                return false;
            }
        }
        return true;
    }

    // =================================================
    // RESULT SCREEN
    // =================================================

    static void showResultScreen(String resultText) {

        frame.dispose();

        frame = new JFrame("MotorPH Employee App - Result");
        frame.setSize(720, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(BACKGROUND);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("Result");
        title.setFont(TITLE_FONT);
        title.setForeground(DARK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(20, 0, 10, 0));
        frame.add(title, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(RESULT_FONT);
        outputArea.setMargin(new Insets(15, 15, 15, 15));
        outputArea.setText(resultText);
        outputArea.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(outputArea);
        scroll.setBorder(new EmptyBorder(10, 30, 10, 30));
        frame.add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 12));
        bottom.setBackground(BACKGROUND);

        JButton backButton = new JButton("Back to Form");
        styleButton(backButton, BLUE);
        backButton.setPreferredSize(new Dimension(160, 40));

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton, RED);
        exitButton.setPreferredSize(new Dimension(140, 40));

        bottom.add(backButton);
        bottom.add(exitButton);
        frame.add(bottom, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            frame.dispose();
            createFeature1FormGUI();
        });

        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    // =================================================
    // Builds the payroll text for the chosen month and year.
    // The actual calculations live in MotorPHPayrollSystem.java -
    // this method just calls them and arranges the output.
    // =================================================

    static String buildPayrollSummary(
            String empNo,
            String[] emp,
            int month,
            int year) {

        String result = "";

        try {
            double hourlyRate =
                    MotorPHPayrollSystem.parseMoney(emp[6]);

            LocalDate firstStart  = LocalDate.of(year, month, 1);
            LocalDate firstEnd    = LocalDate.of(year, month, 15);
            LocalDate secondStart = LocalDate.of(year, month, 16);
            LocalDate secondEnd   = YearMonth.of(year, month).atEndOfMonth();

            double hours1 = MotorPHPayrollSystem
                    .computeHoursForEmployeeInRange(
                            empNo, attendance, firstStart, firstEnd);

            double hours2 = MotorPHPayrollSystem
                    .computeHoursForEmployeeInRange(
                            empNo, attendance, secondStart, secondEnd);

            if (hours1 == 0 && hours2 == 0) {
                return "No attendance records found for this period.\n";
            }

            double[] payroll = MotorPHPayrollSystem
                    .calculatePayrollForCutoff(hours1, hours2, hourlyRate);

            result += "Hourly Rate     : " + money(hourlyRate) + "\n";
            result += "Hours (1-15)    : " + money(hours1)     + "\n";
            result += "Hours (16-end)  : " + money(hours2)     + "\n";
            result += "Gross 1-15      : " + money(payroll[0]) + "\n";
            result += "Gross 16-end    : " + money(payroll[1]) + "\n";
            result += "SSS             : " + money(payroll[4]) + "\n";
            result += "PhilHealth      : " + money(payroll[5]) + "\n";
            result += "Pag-IBIG        : " + money(payroll[6]) + "\n";
            result += "Withholding Tax : " + money(payroll[7]) + "\n";
            result += "Total Deduction : " + money(payroll[8]) + "\n";
            result += "Net 1-15        : " + money(payroll[2]) + "\n";
            result += "Net 16-end      : " + money(payroll[3]) + "\n";
        } catch (Exception ex) {
            result += "Could not compute payroll: " + ex.getMessage() + "\n";
        }

        return result;
    }

    // Rounds a number to 2 decimal places as a String (e.g. 12345.67).
    // Replaces String.format("%,.2f", value) with something simpler.
    static String money(double value) {
        double rounded = Math.round(value * 100.0) / 100.0;
        return String.valueOf(rounded);
    }

    // =================================================
    // HELPERS
    // =================================================

    // Translate month name -> month number (1-12)
    static int monthNameToNumber(String name) {
        switch (name) {
            case "January":   return 1;
            case "February":  return 2;
            case "March":     return 3;
            case "April":     return 4;
            case "May":       return 5;
            case "June":      return 6;
            case "July":      return 7;
            case "August":    return 8;
            case "September": return 9;
            case "October":   return 10;
            case "November":  return 11;
            case "December":  return 12;
            default:          return 1;
        }
    }

    // Show a styled error dialog
    static void showError(String message) {
        JOptionPane.showMessageDialog(
                frame,
                message,
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Counts how many employees match the given name (case-insensitive).
    static int countEmployeeNameMatches(String searchName) {
        String nameLower = searchName.toLowerCase().trim();
        int count = 0;

        for (String empNo : employees.keySet()) {
            String[] emp = employees.get(empNo);
            String firstName = emp[2].toLowerCase().trim();
            String lastName = emp[1].toLowerCase().trim();
            String fullName = (lastName + " " + firstName).toLowerCase().trim();

            if (fullName.contains(nameLower) || firstName.contains(nameLower) || lastName.contains(nameLower)) {
                count++;
            }
        }
        return count;
    }

    // Searches for an employee by name (First + Last name, case-insensitive).
    // Returns the employee record if found, null otherwise.
    static String[] searchEmployeeByName(String searchName) {
        String nameLower = searchName.toLowerCase().trim();

        for (String empNo : employees.keySet()) {
            String[] emp = employees.get(empNo);
            String firstName = emp[2].toLowerCase().trim();
            String lastName = emp[1].toLowerCase().trim();
            String fullName = (lastName + " " + firstName).toLowerCase().trim();

            // Match if search name matches full name or just first or just last
            if (fullName.contains(nameLower) || firstName.contains(nameLower) || lastName.contains(nameLower)) {
                return emp;
            }
        }
        return null;
    }

    // Makes a button look nice and colored.
    // We use this so all the buttons look the same.
    static void styleButton(JButton button, Color color) {

        // These three lines are what make colored buttons actually paint
        // their background instead of falling back to the OS default look.
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);

        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
