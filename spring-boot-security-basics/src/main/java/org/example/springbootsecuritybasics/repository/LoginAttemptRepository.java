package org.example.springbootsecuritybasics.repository;

import org.example.springbootsecuritybasics.model.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
}
