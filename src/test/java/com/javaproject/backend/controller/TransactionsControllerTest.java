package com.javaproject.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.backend.model.Transaction;
import com.javaproject.backend.repository.RepoTransactions;

import org.hibernate.annotations.TimeZoneStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import java.time.LocalDate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepoTransactions repoTransactions;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateATransaction() throws Exception{
        Transaction transaction = new Transaction();
        transaction.setAmount(150.0);
        transaction.setDate(LocalDate.of(2024,2,24));
        transaction.setName("Valdes Felipe");
        transaction.setAccount(null);
    

    when(repoTransactions.save(any(Transaction.class))).thenReturn(transaction);
        
    mockMvc.perform(post("/transactions").contentType("application/json").content(objectMapper.writeValueAsString(transaction))).andExpect(status().isOk());
    
    
}

    @Test
    void shouldGiveAnErrorWithPost() throws Exception{
        Transaction transaction = new Transaction();
        transaction.setAmount(-50.0);
        transaction.setDate(null);
        transaction.setName("Valdes Felipe");
        transaction.setAccount(null);

    when(repoTransactions.save(any(Transaction.class))).thenReturn(transaction);

    mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transaction))).andExpect(status().isBadRequest());
    
    
}



    @Test
    void shouldDeleteATransaction() throws Exception{
        String id = "test-id";
        doNothing().when(repoTransactions).deleteById(id);

        mockMvc.perform(delete("/transactions/{id}", id)).andExpect(status().isNoContent());

        verify(repoTransactions, times(1)).deleteById(id);
    }

    @Test
    void shouldGetATransaction() throws Exception{
        
        String id="test-id";

        Transaction transaction = new Transaction();
        
        transaction.setAmount(150.0);
        transaction.setDate(LocalDate.of(2024,2,24));
        transaction.setName("Valdes Felipe");
        transaction.setAccount(null);

        when(repoTransactions.findById(id)).thenReturn(Optional.of(transaction));

        mockMvc.perform(get("/transactions/{id}",id)).andExpect(status().isOk());

        verify(repoTransactions, times(1)).findById(id);
    }

    @Test
    void shouldUpdateATransaction() throws Exception{

        String id="test-id";

        Transaction transaction = new Transaction();

        transaction.setAmount(150.0);
        transaction.setDate(LocalDate.of(2024,2,24));
        transaction.setName("Valdes Felipe");
        transaction.setAccount(null);

        Transaction updatedTransaction = new Transaction();

        updatedTransaction.setAmount(50.0);
        updatedTransaction.setDate(LocalDate.of(2024,2,24));
        updatedTransaction.setName("Valdes Feli");
        updatedTransaction.setAccount(null);

        when(repoTransactions.findById(id)).thenReturn(Optional.of(transaction));

        when(repoTransactions.save(any(Transaction.class))).thenReturn(updatedTransaction);

        mockMvc.perform(put("/transactions/{id}",id).contentType("application/json").content(objectMapper.writeValueAsString(updatedTransaction))).andExpect(status().isOk());

        verify(repoTransactions, times(1)).findById(id);
        verify(repoTransactions, times(1)).save(any(Transaction.class));

    }
}
