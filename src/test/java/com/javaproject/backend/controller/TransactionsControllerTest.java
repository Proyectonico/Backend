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

import java.time.LocalDate;

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
        transaction.setMonto(150.0);
        transaction.setFecha(LocalDate.of(2024,2,24));
        transaction.setNombre("Valdes Felipe");
        transaction.setDescripcion("Compra de caramelos");
    

    when(repoTransactions.save(any(Transaction.class))).thenReturn(transaction);
        
    mockMvc.perform(post("/transacciones").contentType("application/json").content(objectMapper.writeValueAsString(transaction))).andExpect(status().isOk());
    }

    @Test
    void shouldGiveAnErrorWithPost() throws Exception{
        Transaction transaction = new Transaction();
        transaction.setMonto(-50.0);
        transaction.setFecha(null);
        transaction.setNombre("Valdes Felipe");
        transaction.setDescripcion(null);

    when(repoTransactions.save(any(Transaction.class))).thenReturn(transaction);

    mockMvc.perform(post("/transacciones").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transaction))).andExpect(status().isBadRequest());
    }
}
