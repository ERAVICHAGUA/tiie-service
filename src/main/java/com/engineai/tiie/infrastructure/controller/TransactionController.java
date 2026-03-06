package com.engineai.tiie.infrastructure.controller;

import com.engineai.tiie.application.service.TransactionService;
import com.engineai.tiie.domain.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody CreateTransactionRequest request) {
        return transactionService.create(request);
    }

    @GetMapping
    public List<Transaction> listByUserId(@RequestParam Long userId) {
        return transactionService.findByUserId(userId);
    }
}