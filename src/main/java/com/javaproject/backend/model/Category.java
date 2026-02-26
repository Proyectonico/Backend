package com.javaproject.backend.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.PrePersist;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Category {
	@Id
	@Column(nullable = false, updatable = false)
	private String id;

	@PrePersist
	public void generatedId() {
		if (this.id == null) {
			this.id = UUID.randomUUID().toString();
		}
	}

	@NotBlank(message = "this field can not be empty")
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Category> children = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}
}
