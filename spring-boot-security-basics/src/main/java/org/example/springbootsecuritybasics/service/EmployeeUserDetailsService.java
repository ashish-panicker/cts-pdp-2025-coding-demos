package org.example.springbootsecuritybasics.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.repository.EmployeeRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var emp = employeeRepository.findByUserName(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)
        );
        return new User(
                emp.getUserName(),
                emp.getPassword(),
                emp.isEnabled(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_"+emp.getRole()))
        );
    }
}
