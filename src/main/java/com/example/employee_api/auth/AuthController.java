package com.example.employee_api.auth;

import com.example.employee_api.auth.dto.LoginRequest;
import com.example.employee_api.auth.dto.LoginResponse;
import com.example.employee_api.auth.dto.RegisterRequest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.LoginRequest;
//import com.example.employee_api.auth.dto.LoginResponse;
//import com.example.employee_api.auth.dto.RegisterRequest;
//import com.example.employee_api.entity.User;
//import com.example.employee_api.exception.UsernameAlreadyExistsException;
//import com.example.employee_api.repository.UserRepository;
//import com.example.employee_api.security.JwtUtil;
//
//import jakarta.validation.Valid;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthController(UserRepository userRepository,
//                          PasswordEncoder passwordEncoder,
//                          JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//    // ========================
//    // LOGIN
//    // ========================
//    @PostMapping("/login")
//    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
//
//        User user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        String token = jwtUtil.generateToken(user.getUsername());
//        return new LoginResponse(token);
//    }
//
//    // ========================
//    // REGISTER
//    // ========================
//    @PostMapping("/register")
//    public String register(@Valid @RequestBody RegisterRequest request) {
//
//        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
//            throw new UsernameAlreadyExistsException("Username already exists");
//        }
//
//        User newUser = new User();
//        newUser.setUsername(request.getUsername());
//        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        newUser.setRole(request.getRole());
//
//        userRepository.save(newUser);
//
//        return "User registered successfully";
//    }
//}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.LoginRequest;
//import com.example.employee_api.auth.dto.LoginResponse;
//import com.example.employee_api.auth.dto.RegisterRequest;
//import com.example.employee_api.entity.User;
//import com.example.employee_api.exception.InvalidPasswordException;
//import com.example.employee_api.repository.UserRepository;
//import com.example.employee_api.security.JwtUtil;
//
//import jakarta.validation.Valid;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.employee_api.exception.UsernameAlreadyExistsException;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthController(UserRepository userRepository,
//                          PasswordEncoder passwordEncoder,
//                          JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/login")
//    public LoginResponse login(@RequestBody LoginRequest request) {
//
//        User user = userRepository
//                .findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new InvalidPasswordException("Invalid password");
////            throw new RuntimeException("Invalid password");
//        }
//
//        String token = jwtUtil.generateToken(user.getUsername());
//
//        return new LoginResponse(token);
//    }
//
//    @PostMapping("/register")
////    public String register(@RequestBody User request)
//    public String register(@Valid @RequestBody RegisterRequest request){
//
//        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
//            throw new UsernameAlreadyExistsException("Username already exists");
//        }
//
//        User newUser = new User();
//        newUser.setUsername(request.getUsername());
//        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
//        //newUser.setRole(request.getRole());
//        newUser.setRole("USER");
//
////        newUser.setEmail(request.getEmail());
//
//        userRepository.save(newUser);
//
//        return "User registered successfully";
//    }
//}
