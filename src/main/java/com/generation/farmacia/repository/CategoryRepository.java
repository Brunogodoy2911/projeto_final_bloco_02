package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findAllByNameContainingIgnoreCase(String name);

  List<Category> findAllByDescriptionContainingIgnoreCase(String description);

}
