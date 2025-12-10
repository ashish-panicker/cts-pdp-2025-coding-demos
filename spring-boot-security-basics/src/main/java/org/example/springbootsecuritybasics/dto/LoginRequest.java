package org.example.springbootsecuritybasics.dto;

public record LoginRequest(
        String userName,
        String password
) {}