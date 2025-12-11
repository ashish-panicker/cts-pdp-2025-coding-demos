package org.example.springbootsecuritybasics.service.security;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.model.LoginAttempt;
import org.example.springbootsecuritybasics.repository.LoginAttemptRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoginAttemptService {

    private final LoginAttemptRepository loginAttemptRepository;

    public void record(String userName, boolean success, String ip, String ua, String reason) {
        LoginAttempt attempt = LoginAttempt.builder()
                .userName(userName)
                .success(success)
                .ipAddress(ip)
                .userAgent(ua)
                .reason(reason)
                .attemptedAt(Instant.now())
                .build();
        loginAttemptRepository.save(attempt);
    }
}
