package com.example.employee_api.auth;

import com.example.employee_api.auth.dto.LoginRequest;
import com.example.employee_api.auth.dto.RegisterRequest;
import com.example.employee_api.entity.User;
import com.example.employee_api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole("USER");

        userRepository.save(user);

        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return "LOGIN SUCCESS: " + user.getUsername();
    }
}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.LoginRequest;
//import com.example.employee_api.auth.dto.LoginResponse;
//import com.example.employee_api.security.JwtUtil;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final JwtUtil jwtUtil;
//
//    public AuthController(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//
//        String token = jwtUtil.generateToken(request.getUsername());
//
//        return new LoginResponse(token);
//    }
//}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.*;
//import com.example.employee_api.security.JwtUtil;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final JwtUtil jwtUtil;
//
//    public AuthController(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//
//        String token = jwtUtil.generateToken(request.getUsername());
//
//        return new LoginResponse(token);
//    }
//}
