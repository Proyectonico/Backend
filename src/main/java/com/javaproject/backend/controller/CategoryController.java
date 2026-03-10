package com.javaproject.backend.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.javaproject.backend.repository.RepoCategory;
import com.javaproject.backend.model.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private RepoCategory repoCategory;

	@PostMapping()
	public ResponseEntity<Category> CreateCategory(@Valid @RequestBody Category category) {

		Category saved = repoCategory.save(category);
		return ResponseEntity.ok(saved);
	}

	@GetMapping()
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = repoCategory.findAll();
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable String id) {
		return repoCategory.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
		repoCategory.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable String id, @RequestBody @Valid Category category) {

		return repoCategory.findById(id).map(existing -> {
			existing.setName(category.getName());
			existing.setParent(category.getParent());

			return ResponseEntity.ok(repoCategory.save(existing));
		}).orElse(ResponseEntity.notFound().build());
	}

}
