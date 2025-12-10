package org.example.springbootrefresher.banking.controller;

import org.example.springbootrefresher.banking.model.Transaction;
import org.example.springbootrefresher.banking.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/top")
    public List<Transaction> getTopTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            String sortBy, String direction) {
        Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() :  Sort.by(sortBy).ascending();
        PageRequest request = PageRequest.of(page, size, sort);
        Page<Transaction> transactions = transactionService.getTopTransactions(page, size);
        return null;
    }
}
