package org.example.springbootsecuritybasics.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.dto.EmployeeDto;
import org.example.springbootsecuritybasics.model.Employee;
import org.example.springbootsecuritybasics.service.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * | HTTP Method | Endpoint                         | Description           | Role  |
 * | ----------- | -------------------------------- | --------------------- | ----- |
 * | `POST`      | `/admin/create`                  | Create a new employee | ADMIN |
 * | `GET`       | `/admin/list`                    | List all employees    | ADMIN |
 * | `PUT`       | `/admin/toggle-status/{id}`      | Enable/Disable user   | ADMIN |
 * | `PUT`       | `/admin/update-role/{id}/{role}` | Change role           | ADMIN |
 * | `DELETE`    | `/admin/delete/{id}`             | Delete employee       | ADMIN |
 */

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String createEmployee(@RequestBody EmployeeDto dto) {

        if (employeeService.findByUserName(dto.username()).isPresent()) {
            return "Username already exists!";
        }

        Employee emp = Employee.builder()
                .userName(dto.username())
                .password(passwordEncoder.encode("default123")) // Default password
                .role(dto.role())
                .isEnabled(dto.enabled())
                .build();

        employeeService.save(emp);
        return "Employee created successfully!";
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
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

    @PutMapping("/toggle-status/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String toggleStatus(@PathVariable Long id) {

        Employee emp = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setEnabled(!emp.isEnabled());
        employeeService.save(emp);

        return "Employee " + emp.getUserName() +
                " status changed to: " + (emp.isEnabled() ? "ENABLED" : "DISABLED");
    }

    @PutMapping("/update-role/{id}/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRole(@PathVariable Long id, @PathVariable String role) {

        Employee emp = employeeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        emp.setRole(role.toUpperCase());
        employeeService.save(emp);

        return "Role updated for " + emp.getUserName() +
                " → " + emp.getRole();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEmployee(@PathVariable Long id) {

        employeeService.deleteById(id);
        return "Employee deleted successfully!";
    }
}
