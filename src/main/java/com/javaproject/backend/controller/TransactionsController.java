package com.javaproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import com.javaproject.backend.model.Transaction;
import com.javaproject.backend.repository.RepoTransactions;

@RestController
@RequestMapping("/transacciones")
public class TransactionsController {

    @Autowired
    private RepoTransactions repoTransactions;

    @PostMapping
    public Transaction crearTransaccion(@Valid @RequestBody Transaction transaction) {
        return repoTransactions.save(transaction);
    }
}

