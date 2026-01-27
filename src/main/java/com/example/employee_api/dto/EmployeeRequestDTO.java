package com.example.employee_api.dto;

import jakarta.validation.constraints.*;
//import jakarta.validation.*;
import lombok.Data;

@Data
public class EmployeeRequestDTO {

    @NotBlank(message = "Name wajib diisi")
    private String name;

    @Email(message = "Email tidak valid")
    @NotBlank(message = "Email wajib diisi")
    private String email;

    @NotBlank(message = "Department wajib diisi")
    private String department;

    @NotNull(message = "Salary wajib diisi")
    @Positive(message = "Salary harus lebih dari 0")
    private Double salary;
}
