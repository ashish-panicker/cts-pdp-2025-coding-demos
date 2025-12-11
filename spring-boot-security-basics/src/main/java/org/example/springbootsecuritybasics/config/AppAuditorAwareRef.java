package org.example.springbootsecuritybasics.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/*
 * AuditorAware: Plugs into Spring Data JPA and supply the auditor aka current user
 * Enables audit trails and helps to populate @CreatedBy @ModifiedBy fields
 */
@Component("auditAwareRef")
@Primary
@Slf4j
public class AppAuditorAwareRef implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        var securityContext = SecurityContextHolder.getContext();
        var auth = securityContext.getAuthentication();
        if(auth != null && !auth.isAuthenticated()) {
            return Optional.of("SYSTEM");
        }
        var principal = auth.getPrincipal();
        if(principal instanceof UserDetails ud) {
            return Optional.of(ud.getUsername());
        }
        return Optional.of(String.valueOf(principal));
    }
}
