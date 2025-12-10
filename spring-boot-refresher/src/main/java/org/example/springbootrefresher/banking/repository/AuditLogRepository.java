package org.example.springbootrefresher.banking.repository;

import org.example.springbootrefresher.banking.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
