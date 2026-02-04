package com.javaproject.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaproject.backend.model.Category;
import com.javaproject.backend.repository.RepoCategory;

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
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
@Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepoCategory repoCategory;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateACategory() throws Exception{
        Category category = new Category();
        category.setName("Supermercado");

    when(repoCategory.save(any(Category.class))).thenReturn(category);
        
    mockMvc.perform(post("/category").contentType("application/json").content(objectMapper.writeValueAsString(category))).andExpect(status().isOk());
    
    
}

    void shouldCreateACategoryWithParent() throws Exception{

        Category parent = new Category();
        parent.setId("test-id");
        parent.setName("Farmacia");

        Category category = new Category();
        category.setName("Supermercado");
        category.setParent(parent);

    when(repoCategory.save(any(Category.class))).thenReturn(category);
        
    mockMvc.perform(post("/category").contentType("application/json").content(objectMapper.writeValueAsString(category))).andExpect(status().isOk());
    
    
}


    @Test
    void shouldDeleteACateegory() throws Exception{
        String id = "test-id";
        doNothing().when(repoCategory).deleteById(id);

        mockMvc.perform(delete("/category/{id}", id)).andExpect(status().isNoContent());

        verify(repoCategory, times(1)).deleteById(id);
    }

    @Test
    void shouldGetACategory() throws Exception{
        
        String id="test-id";

        Category category = new Category();
        
        category.setName("Supermercado");
        

        when(repoCategory.findById(id)).thenReturn(Optional.of(category));

        mockMvc.perform(get("/category/{id}",id)).andExpect(status().isOk());

        verify(repoCategory, times(1)).findById(id);
    }

    @Test
    void shouldUpdateACategory() throws Exception{

        String id="test-id";

        Category category = new Category();

        category.setName("Supermercado");

        Category updatedCategory = new Category();

        updatedCategory.setName("Farmacia");
        
        when(repoCategory.findById(id)).thenReturn(Optional.of(category));

        when(repoCategory.save(any(Category.class))).thenReturn(updatedCategory);

        mockMvc.perform(put("/category/{id}",id).contentType("application/json").content(objectMapper.writeValueAsString(updatedCategory))).andExpect(status().isOk());

        verify(repoCategory, times(1)).findById(id);
        verify(repoCategory, times(1)).save(any(Category.class));

    }
}
