package com.example.employee_api.controller;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.service.EmployeeService;
import com.example.employee_api.dto.*;
import com.example.employee_api.response.*;
import com.example.employee_api.exception.GlobalExceptionHandler.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> create(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        Employee emp = employeeService.save(EmployeeMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created",
                        EmployeeMapper.toResponse(emp)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAll() {
        List<EmployeeResponseDTO> data = employeeService.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(new ApiResponse<>(true, "Success", data));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getById(
            @PathVariable Long id) {

        Employee employee = employeeService.findById(id);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setName(employee.getName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setDepartment(employee.getDepartment());
        responseDTO.setSalary(employee.getSalary());

        ApiResponse<EmployeeResponseDTO> response =
                new ApiResponse<>(true, "Employee found", responseDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>> deleteById(
            @PathVariable Long id) {

        employeeService.deleteById(id);

        ApiResponse<Object> response =
                new ApiResponse<>(true, "Employee deleted successfully", null);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> updateById(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO request) {

        Employee updatedEmployee =
                employeeService.updateById(id, request);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(updatedEmployee.getId());
        responseDTO.setName(updatedEmployee.getName());
        responseDTO.setEmail(updatedEmployee.getEmail());
        responseDTO.setDepartment(updatedEmployee.getDepartment());
        responseDTO.setSalary(updatedEmployee.getSalary());

        ApiResponse<EmployeeResponseDTO> response =
                new ApiResponse<>(true, "Employee updated successfully", responseDTO);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> patchById(
            @PathVariable Long id,
            @Valid @RequestBody EmployeePatchRequestDTO request) {

        Employee updatedEmployee =
                employeeService.patchById(id, request);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(updatedEmployee.getId());
        responseDTO.setName(updatedEmployee.getName());
        responseDTO.setEmail(updatedEmployee.getEmail());
        responseDTO.setDepartment(updatedEmployee.getDepartment());
        responseDTO.setSalary(updatedEmployee.getSalary());

        ApiResponse<EmployeeResponseDTO> response =
                new ApiResponse<>(true, "Employee updated partially", responseDTO);

        return ResponseEntity.ok(response);
    }
}