package org.example.springbootsecuritybasics.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "login_attempts", indexes = {
        @Index(name = "idx_login_username", columnList = "user_name"),
        @Index(name = "idx_login_time", columnList = "attempted_at")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // attempted username (may be null)
    private String userName;

    // true if login succeeded
    private boolean success;

    // optional: caller IP
    private String ipAddress;

    // optional
    private String userAgent;

    // optional: "BAD_CREDENTIALS", "USER_NOT_FOUND", etc.
    private String reason;

    @Column(name = "attempted_at", nullable = false)
    private Instant attemptedAt;
}
