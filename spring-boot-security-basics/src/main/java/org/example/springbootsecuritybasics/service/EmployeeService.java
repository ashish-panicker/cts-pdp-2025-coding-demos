package org.example.springbootsecuritybasics.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootsecuritybasics.model.Employee;
import org.example.springbootsecuritybasics.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Optional<Employee> findByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
