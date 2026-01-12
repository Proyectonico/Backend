package com.javaproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.backend.model.Transaction;

public interface RepoTransactions extends JpaRepository<Transaction, Long> {
}
