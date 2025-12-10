package org.example.springbootrefresher.banking.repository;

import org.example.springbootrefresher.banking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("select a from BankAccount a where a.balance < :minBalance")
    List<BankAccount> findAccountBelowMinBalance(@Param("minBalance") double minBalance);

    @Query(name = "BankAccount.findByAccountNumber")
    BankAccount findByAccountNumber(String accNo);

    @Query(name = "BankAccount.findHighValueAccounts", nativeQuery = true)
    List<BankAccount> findHighValueAccounts(Double amount);
}
