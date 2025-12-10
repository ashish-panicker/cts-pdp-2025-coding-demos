package org.example.springbootrefresher.banking.repository;

import org.example.springbootrefresher.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Top 5 highest transactions
    @Query(value = """
            select * from transactions order by amount desc limit 5
            """, nativeQuery = true)
    List<Transaction> findTopTransactions();
}
