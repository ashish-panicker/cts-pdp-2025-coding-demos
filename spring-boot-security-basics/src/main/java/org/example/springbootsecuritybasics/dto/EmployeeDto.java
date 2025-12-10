package org.example.springbootsecuritybasics.dto;

public record EmployeeDto(
        Long id,
        String username,
        String role,
        boolean enabled
) {}