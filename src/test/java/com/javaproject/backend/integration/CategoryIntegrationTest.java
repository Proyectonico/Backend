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

import com.javaproject.backend.repository.RepoCategory;
import com.javaproject.backend.model.Category;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class CategoryIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RepoCategory repoCategory;

    @Autowired 
    ObjectMapper objectMapper;

    @Test 
    void ShouldCreateAndRetrieveCategory() throws Exception{

        Category categoryParent = new Category();
        categoryParent.setName("Farmacia");

        String response = mockMvc.perform(post("/category")
        .contentType("application/json").content(objectMapper.writeValueAsString(categoryParent)))
        .andReturn().getResponse().getContentAsString();

        Category savedParent = objectMapper.readValue(response, Category.class);

        Category categoryChild = new Category();
        categoryChild.setName("Remedios");
        categoryChild.setParent(savedParent);

        String responseChild = mockMvc.perform(post("/category")
        .contentType("application/json").content(objectMapper.writeValueAsString(categoryChild)))
        .andReturn().getResponse().getContentAsString();

        Category savedChild = objectMapper.readValue(responseChild, Category.class);

        mockMvc.perform(get("/category/" + savedChild.getId())).andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(savedChild.getId()))
        .andExpect(jsonPath("$.name").value("Remedios"))
        .andExpect(jsonPath("$.parent.id").value(savedParent.getId()));


    }
}
