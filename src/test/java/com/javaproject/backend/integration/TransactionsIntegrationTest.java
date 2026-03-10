package com.javaproject.backend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.javaproject.backend.model.Transaction;
import com.javaproject.backend.repository.RepoTransactions;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TransactionsIntegrationTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	RepoTransactions repo;

	@Autowired
	ObjectMapper mapper;

	@Test
	void shouldCreateAndRetrieveTransaction() throws Exception {

		Transaction t = new Transaction();
		t.setAmount(1000.0);
		t.setDate(LocalDate.of(2024, 2, 24));
		t.setName("Valdes Felipe");
		t.setAccount(null);

		String response = mockMvc
				.perform(post("/transactions").contentType("application/json").content(mapper.writeValueAsString(t)))
				.andReturn().getResponse().getContentAsString();

		Transaction saved = mapper.readValue(response, Transaction.class);

		mockMvc.perform(get("/transactions/" + saved.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(saved.getId())).andExpect(jsonPath("$.amount").value(1000.0))
				.andExpect(jsonPath("$.date").value("2024-02-24")).andExpect(jsonPath("$.name").value(saved.getName()));
	}
}
