package org.example.springbootrefresher.banking.loader;

import lombok.extern.slf4j.Slf4j;
import org.example.springbootrefresher.banking.model.*;
import org.example.springbootrefresher.banking.model.constants.AccountStatus;
import org.example.springbootrefresher.banking.model.constants.TransactionType;
import org.example.springbootrefresher.banking.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@Slf4j
public class SampleDataLoader {

    @Bean
    public CommandLineRunner loadData(
            BranchRepository branchRepository,
            CustomerRepository customerRepository,
            BankAccountRepository bankAccountRepository,
            TransactionRepository transactionRepository,
            AuditLogRepository auditLogRepository
    ) {
        return args -> {

            Branch branch1 = new Branch();
            branch1.setCode("BLR001");
            branch1.setName("Bangalore Main Branch");
            branch1.setAddress("MG Road, Bangalore");

            Branch branch2 = new Branch();
            branch2.setCode("MUM002");
            branch2.setName("Mumbai Central Branch");
            branch2.setAddress("Andheri East, Mumbai");

            branchRepository.save(branch1);
            branchRepository.save(branch2);

            Customer customer1 = new Customer();
            customer1.setFullName("Rahul Sharma");
            customer1.setEmail("rahul.sharma@example.com");
            customer1.setPhone("9876543210");
            customer1.setBranch(branch1);

            Customer customer2 = new Customer();
            customer2.setFullName("Anita Desai");
            customer2.setEmail("anita.desai@example.com");
            customer2.setPhone("9123456780");
            customer2.setBranch(branch2);

            customerRepository.save(customer1);
            customerRepository.save(customer2);

            customer1.setEmail("rahul.sharma@gmail.com");
            customerRepository.save(customer1);

            customer1.setPhone("9876543211");
            customerRepository.save(customer1);

            AuditLog log1 = new AuditLog();
            log1.setAction("CUSTOMER_CREATED");
            log1.setPerformedBy("SYSTEM");
            log1.setTimestamp(LocalDateTime.now().minusDays(2));
            log1.setDetails("Customer Rahul Sharma registered at Bangalore branch.");

            AuditLog log2 = new AuditLog();
            log2.setAction("ACCOUNT_OPENED");
            log2.setPerformedBy("SYSTEM");
            log2.setTimestamp(LocalDateTime.now().minusDays(1));
            log2.setDetails("Account ACC1001 created for Rahul Sharma.");

            auditLogRepository.save(log1);
            auditLogRepository.save(log2);

            BankAccount acc1 = new BankAccount();
            acc1.setAccountNumber("ACC1001");
            acc1.setBalance(new BigDecimal("50000"));
            acc1.setStatus(AccountStatus.ACTIVE);
            acc1.setCustomer(customer1);

            BankAccount acc2 = new BankAccount();
            acc2.setAccountNumber("ACC2001");
            acc2.setBalance(new BigDecimal("75000"));
            acc2.setStatus(AccountStatus.ACTIVE);
            acc2.setCustomer(customer2);

            bankAccountRepository.save(acc1);
            bankAccountRepository.save(acc2);

            Transaction t1 = new Transaction();
            t1.setAccount(acc1);
            t1.setAmount(new BigDecimal("5000"));
            t1.setType(TransactionType.DEBIT);
            t1.setTimestamp(LocalDateTime.now().minusDays(2));
            t1.setDescription("ATM Withdrawal - MG Road");

            Transaction t2 = new Transaction();
            t2.setAccount(acc1);
            t2.setAmount(new BigDecimal("15000"));
            t2.setType(TransactionType.CREDIT);
            t2.setTimestamp(LocalDateTime.now().minusDays(1));
            t2.setDescription("Salary Deposit");

            Transaction t3 = new Transaction();
            t3.setAccount(acc2);
            t3.setAmount(new BigDecimal("2500"));
            t3.setType(TransactionType.DEBIT);
            t3.setTimestamp(LocalDateTime.now().minusDays(3));
            t3.setDescription("POS Purchase - Big Bazaar");

            transactionRepository.save(t1);
            transactionRepository.save(t2);
            transactionRepository.save(t3);


        };
    }
}
