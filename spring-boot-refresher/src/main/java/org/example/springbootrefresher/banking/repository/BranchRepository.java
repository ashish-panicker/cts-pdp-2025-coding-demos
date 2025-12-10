package org.example.springbootrefresher.banking.repository;

import org.example.springbootrefresher.banking.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findByCode(String code);

    List<Branch> findByNameContainingIgnoreCase(String namePart);
}

