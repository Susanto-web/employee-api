package com.example.employee_api.dto;

import jakarta.validation.constraints.Email;

public class EmployeePatchRequestDTO {

    private String name;

    @Email
    private String email;

    private String department;

    private Double salary;

    // getter & setter

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
