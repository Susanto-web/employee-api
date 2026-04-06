package com.example.employee_api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username wajib diisi (Username cannot be blank)")
    private String username;

    @NotBlank(message = "Password wajib diisi (Password cannot be blank)")
    private String password;

    @NotBlank(message = "Role wajib diisi (Role cannot be blank)")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}