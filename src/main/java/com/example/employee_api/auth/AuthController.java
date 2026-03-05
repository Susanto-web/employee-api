package com.example.employee_api.auth;

import com.example.employee_api.auth.dto.LoginRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return "LOGIN OK: " + request.getUsername();
    }
}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.response.ApiResponse;
//import com.example.employee_api.security.JwtUtil;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController<LoginRequest> {
//
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ApiResponse<String> login(@RequestBody LoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getUsername(), request.getPassword())
//        );
//
//        String token = jwtUtil.generateToken(request.getUsername());
//        return new ApiResponse<>(true, "Login success", token);
//    }
//}