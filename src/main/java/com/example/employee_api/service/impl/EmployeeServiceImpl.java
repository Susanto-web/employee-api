package com.example.employee_api.service.impl;

import com.example.employee_api.entity.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import com.example.employee_api.service.EmployeeService;
import com.example.employee_api.exception.ResourceNotFoundException;
import com.example.employee_api.dto.EmployeeRequestDTO;
import com.example.employee_api.dto.EmployeePatchRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

//    @Override
//    public Employee findById(Long id) {
//        return employeeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Override
    public Employee updateById(Long id, EmployeeRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        return employeeRepository.save(employee);
    }


//    @Override
//    public Employee updateById(Long id, Employee employee) {
//        Employee existing = findById(id);
//        existing.setName(employee.getName());
//        existing.setEmail(employee.getEmail());
//        existing.setDepartment(employee.getDepartment());
//        existing.setSalary(employee.getSalary());
//        return employeeRepository.save(existing);
//    }

    @Override
    public void deleteById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);
    }

//    @Override
//    public void delete(Long id) {
//        employeeRepository.deleteById(id);
//    }

    @Override
    public Employee patchById(Long id, EmployeePatchRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found"));

        if (request.getName() == null
                && request.getEmail() == null
                && request.getDepartment() == null
                && request.getSalary() == null) {
            throw new IllegalArgumentException("No data to update");
        }

        if (request.getName() != null) {
            employee.setName(request.getName());
        }

        if (request.getEmail() != null) {
            employee.setEmail(request.getEmail());
        }

        if (request.getDepartment() != null) {
            employee.setDepartment(request.getDepartment());
        }

        if (request.getSalary() != null) {
            employee.setSalary(request.getSalary());
        }

        return employeeRepository.save(employee);
    }


}
