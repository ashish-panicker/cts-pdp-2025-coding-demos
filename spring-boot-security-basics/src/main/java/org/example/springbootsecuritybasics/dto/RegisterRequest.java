package org.example.springbootsecuritybasics.dto;

public record RegisterRequest(
        String userName,
        String password,
        String role
) {}
