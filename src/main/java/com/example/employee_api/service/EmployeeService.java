package com.example.employee_api.service;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.dto.EmployeeRequestDTO;
import com.example.employee_api.dto.EmployeePatchRequestDTO;
import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    List<Employee> findAll();
    Employee findById(Long id);
    Employee updateById(Long id, EmployeeRequestDTO request);
    void deleteById(Long id);
    Employee patchById(Long id, EmployeePatchRequestDTO request);
}
