package com.example.employeemanagementsystem.ModelClass;

public class Employee {
    private String employeeID;
    private String employeeJoiningDate;
    private String employeeFullName;
    private String employeeDesignation;
    private String employeeMobileNo;
    private String employeeWorkDetails;
    private String employeeSalary;
    private String employeeAttendance;

    public Employee(){  // needed for Firebase to work
    }

    public Employee(String employeeID, String employeeJoiningDate, String employeeFullName,
                    String employeeDesignation, String employeeMobileNo, String employeeWorkDetails, String employeeSalary, String employeeAttendance) {
        this.employeeID =          employeeID;
        this.employeeJoiningDate = employeeJoiningDate;
        this.employeeFullName =    employeeFullName;
        this.employeeDesignation = employeeDesignation;
        this.employeeMobileNo =    employeeMobileNo;
        this.employeeWorkDetails = employeeWorkDetails;
        this.employeeSalary =      employeeSalary;
        this.employeeAttendance =  employeeAttendance;
    }

    public String getEmployeeID() { return employeeID; }

    public void setEmployeeID(String employeeID) { this.employeeID = employeeID; }

    public String getEmployeeJoiningDate() { return employeeJoiningDate; }

    public void setEmployeeJoiningDate(String employeeJoiningDate) { this.employeeJoiningDate = employeeJoiningDate; }

    public String getEmployeeFullName() { return employeeFullName; }

    public void setEmployeeFullName(String employeeFullName) { this.employeeFullName = employeeFullName; }

    public String getEmployeeDesignation() { return employeeDesignation; }

    public void setEmployeeDesignation(String employeeDesignation) { this.employeeDesignation = employeeDesignation; }

    public String getEmployeeMobileNo() { return employeeMobileNo; }

    public void setEmployeeMobileNo(String employeeMobileNo) { this.employeeMobileNo = employeeMobileNo; }

    public String getEmployeeWorkDetails() { return employeeWorkDetails; }

    public void setEmployeeWorkDetails(String employeeWorkDetails) { this.employeeWorkDetails = employeeWorkDetails; }

    public String getEmployeeSalary() { return employeeSalary; }

    public void setEmployeeSalary(String employeeSalary) { this.employeeSalary = employeeSalary; }

    public String getEmployeeAttendance() { return employeeAttendance; }

    public void setEmployeeAttendance(String employeeAttendance) { this.employeeAttendance = employeeAttendance; }
}
