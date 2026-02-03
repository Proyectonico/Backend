package com.javaproject.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaproject.backend.model.Category;

public interface RepoCategory extends JpaRepository<Category, String> {

}
