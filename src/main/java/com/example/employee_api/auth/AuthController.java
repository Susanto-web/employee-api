package com.example.employee_api.auth;

import com.example.employee_api.auth.dto.LoginRequest;
import com.example.employee_api.auth.dto.LoginResponse;
import com.example.employee_api.auth.dto.RegisterRequest;
import com.example.employee_api.entity.User;
import com.example.employee_api.repository.UserRepository;
import com.example.employee_api.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(token);
    }

    @PostMapping("/register")
    public String register(@RequestBody User request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());
//        newUser.setEmail(request.getEmail());

        userRepository.save(newUser);



        return "User registered successfully";
    }
}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.LoginRequest;
//import com.example.employee_api.auth.dto.RegisterRequest;
//import com.example.employee_api.entity.User;
//import com.example.employee_api.repository.UserRepository;
//
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserRepository userRepository;
//
//    //private final BCryptPasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder;
//
////    public AuthController(UserRepository userRepository,
////                          BCryptPasswordEncoder passwordEncoder) {
////        this.userRepository = userRepository;
////        this.passwordEncoder = passwordEncoder;
////    }
//    public AuthController(UserRepository userRepository,
//                          PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestBody RegisterRequest request) {
//
//        User user = new User();
//
//        user.setUsername(request.getUsername());
//
//        // ENCRYPT PASSWORD
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        user.setRole("USER");
//
//        userRepository.save(user);
//
//        return "User registered";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest request) {
//
//        User user = userRepository
//                .findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return "LOGIN SUCCESS: " + user.getUsername();
//    }
//}


//package com.example.employee_api.auth;
//
//import com.example.employee_api.auth.dto.LoginRequest;
//import com.example.employee_api.auth.dto.RegisterRequest;
//import com.example.employee_api.entity.User;
//import com.example.employee_api.repository.UserRepository;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private final UserRepository userRepository;
//
//    public AuthController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @PostMapping("/register")
//    public String register(@RequestBody RegisterRequest request) {
//
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setRole("USER");
//
//        userRepository.save(user);
//
//        return "User registered";
//    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest request) {
//
//        User user = userRepository
//                .findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!user.getPassword().equals(request.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return "LOGIN SUCCESS: " + user.getUsername();
//    }
//}
