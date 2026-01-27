package com.example.employee_api.dto;

import lombok.Data;

@Data
public class EmployeeResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String department;
    private Double salary;
}
