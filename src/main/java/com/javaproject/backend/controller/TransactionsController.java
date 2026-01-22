package com.javaproject.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import jakarta.validation.Valid;

import com.javaproject.backend.model.Transaction;
import com.javaproject.backend.repository.RepoTransactions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private RepoTransactions repoTransactions;

    @PostMapping
    public Transaction crearTransaccion(@Valid @RequestBody Transaction transaction) {
        return repoTransactions.save(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction (@PathVariable Long id){
        repoTransactions.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return repoTransactions.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> putTransaction(@PathVariable Long id, @Valid @RequestBody Transaction transaction) {
        return repoTransactions.findById(id).map(existing-> {
            existing.setAmount(transaction.getAmount());
            existing.setDate(transaction.getDate());
            existing.setName(transaction.getName());
            existing.setAccount(transaction.getAccount());

            return ResponseEntity.ok(repoTransactions.save(existing));

        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = repoTransactions.findAll();
        return ResponseEntity.ok(transactions);
    }
    

    
}

