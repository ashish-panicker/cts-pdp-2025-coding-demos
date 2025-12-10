package org.example.springbootrefresher.banking.service;

import org.example.springbootrefresher.banking.model.Transaction;
import org.example.springbootrefresher.banking.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Page<Transaction> getTopTransactions(int page, int size) {
        Pageable request = PageRequest.of(page, size, Sort.by("amount").descending());
        return repository.findByOrderByAmountDesc(request);
    }
}
