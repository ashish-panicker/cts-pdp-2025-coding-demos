package org.example.springbootsecuritybasics.repository;

import org.example.springbootsecuritybasics.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByUserName(String userName);
}
