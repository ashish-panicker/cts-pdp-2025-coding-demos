package org.example.springbootsecuritybasics.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.dto.EmployeeDto;
import org.example.springbootsecuritybasics.model.Employee;
import org.example.springbootsecuritybasics.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public EmployeeDto getLoggedInUser(@AuthenticationPrincipal UserDetails userDetails) {

        Employee emp = employeeService.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new EmployeeDto(
                emp.getId(),
                emp.getUserName(),
                emp.getRole(),
                emp.isEnabled()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public EmployeeDto getEmployee(@PathVariable Long id,
                                   @AuthenticationPrincipal UserDetails userDetails) {

        Employee current = employeeService.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Current user not found"));

        Employee target = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Only ADMIN or the same employee can see profile
        if (!current.getRole().equals("ADMIN") && !current.getId().equals(id)) {
            throw new RuntimeException("Access denied");
        }

        return new EmployeeDto(
                target.getId(),
                target.getUserName(),
                target.getRole(),
                target.isEnabled()
        );
    }

    @PutMapping("/update-profile")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public String updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody EmployeeDto updateDto) {

        Employee emp = employeeService.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Employee cannot change role or enabled flag
        emp.setUserName(updateDto.username());

        employeeService.save(emp);

        return "Profile updated successfully!";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
    public List<EmployeeDto> listEmployees() {

        return employeeService.findAll()
                .stream()
                .map(emp -> new EmployeeDto(
                        emp.getId(),
                        emp.getUserName(),
                        emp.getRole(),
                        emp.isEnabled()
                ))
                .toList();
    }

}
