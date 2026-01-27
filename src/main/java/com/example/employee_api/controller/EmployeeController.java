package com.example.employee_api.controller;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.service.EmployeeService;
import com.example.employee_api.dto.*;
import com.example.employee_api.response.*;
import com.example.employee_api.exception.GlobalExceptionHandler.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;

import java.util.List;

//@RestController
//@RequestMapping("/api/employees")
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @PostMapping
//    public Employee create(@RequestBody Employee employee) {
//        return employeeService.save(employee);
//    }
//
//    @GetMapping
//    public List<Employee> getAll() {
//        return employeeService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Employee getById(@PathVariable Long id) {
//        return employeeService.findById(id);
//    }
//
//    @PutMapping("/{id}")
//    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
//        return employeeService.update(id, employee);
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        employeeService.delete(id);
//    }
//}

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> create(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        Employee emp = employeeService.save(EmployeeMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Employee created",
                        EmployeeMapper.toResponse(emp)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeResponseDTO>>> getAll() {
        List<EmployeeResponseDTO> data = employeeService.findAll()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();

        return ResponseEntity.ok(new ApiResponse<>(true, "Success", data));
    }
}
