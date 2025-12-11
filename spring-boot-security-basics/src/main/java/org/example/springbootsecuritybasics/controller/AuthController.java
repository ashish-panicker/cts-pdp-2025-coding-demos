package org.example.springbootsecuritybasics.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springbootsecuritybasics.dto.LoginRequest;
import org.example.springbootsecuritybasics.dto.LoginResponse;
import org.example.springbootsecuritybasics.dto.RegisterRequest;
import org.example.springbootsecuritybasics.model.Employee;
import org.example.springbootsecuritybasics.service.EmployeeService;
import org.example.springbootsecuritybasics.service.security.JwtService;
import org.example.springbootsecuritybasics.service.security.LoginAttemptService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest servletRequest) {

        var ip = servletRequest.getRemoteAddr();
        var ua = servletRequest.getHeader("User-Agent");

        servletRequest.setAttribute("LOGIN_USER", request.userName());

        log.debug("IP: {}", ip);
        log.debug("User-Agent: {}", ua);

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.userName(),
                        request.password()
                )
        );
        loginAttemptService.record(request.userName(), true, ip, ua, "SUCCESS");
        String token = jwtService.generateToken(auth.getName());
        return new LoginResponse(token);

    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        // Check if username already exists
        if (employeeService.findByUserName(request.userName()).isPresent()) {
            return "Username already exists!";
        }

        Employee employee = Employee.builder()
                .userName(request.userName())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())          // ADMIN or EMPLOYEE
                .isEnabled(true)
                .build();

        employeeService.save(employee);

        return "User registered successfully!";
    }


//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<ApiError> handleUsernameNotFoundException(Exception ex, HttpServletRequest req) {
//        return
//    }

}