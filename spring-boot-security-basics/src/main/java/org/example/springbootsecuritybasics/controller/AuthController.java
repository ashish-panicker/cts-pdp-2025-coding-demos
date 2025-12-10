package org.example.springbootsecuritybasics.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.dto.LoginRequest;
import org.example.springbootsecuritybasics.dto.LoginResponse;
import org.example.springbootsecuritybasics.dto.RegisterRequest;
import org.example.springbootsecuritybasics.model.Employee;
import org.example.springbootsecuritybasics.repository.EmployeeRepository;
import org.example.springbootsecuritybasics.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );
        String token = jwtService.generateToken(auth.getName());
        return new LoginResponse(token);

    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        // Check if username already exists
        if (employeeRepository.findByUserName(request.userName()).isPresent()) {
            return "Username already exists!";
        }

        Employee employee = Employee.builder()
                .userName(request.userName())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())          // ADMIN or EMPLOYEE
                .isEnabled(true)
                .build();

        employeeRepository.save(employee);

        return "User registered successfully!";
    }
}