package com.example.employee_api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/secure")
public class EmployeeSecureController {

    @GetMapping("/hello")
    public String secureHello() {
        return "Hello Secure Endpoint! You are authenticated.";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "This is your secure profile data.";
    }
}