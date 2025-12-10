package org.example.springbootrefresher.banking.repository;

import org.example.springbootrefresher.banking.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Query("select a from BankAccount a where a.balance < :minBalance")
    List<BankAccount> findAccountBelowMinBalance(@Param("minBalance") double minBalance);

    @Query(name = "BankAccount.findByAccountNumber")
    BankAccount findByAccountNumber(String accNo);

    @Query(name = "BankAccount.findHighValueAccounts", nativeQuery = true)
    List<BankAccount> findHighValueAccounts(Double amount);

}

/*
 * Since we are not creating implementations for these repository interfaces,
 * how are repository objects created?
 *
 * Spring uses either JDK dynamic proxies or CGLIB to create a runtime implementation
 * 1. Scan for all repositories
 * 2. Each repository is registered as a RepositoryFactoryBean, JpaRepositoryFactoryBean
 * 3. JpaRepositoryFactory creates the actual proxy
 * 4. JpaRepositoryFactory will build SimpleJpaRepository and QueryLookUpStrategy for CRUD operations
 *
 * How does
 * @Query("select a from BankAccount a where a.balance < :minBalance")
 * @Query(name = "BankAccount.findByAccountNumber")
 * work?
 *
 * Spring will convert it into
 * Query query = entityManager.createQuery("select a from BankAccount a where a.balance < :minBalance")
 * query.setParameter("minBalance", value)
 *
 * Query query = entityManager.createNamedQuery("BankAccount.findByAccountNumber")
 */