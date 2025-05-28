package com.generation.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Category;
import com.generation.farmacia.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(categoryService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Category>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(categoryService.getByName(name));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<Category>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(categoryService.getByDescription(description));
  }

  @PostMapping
  public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
    Category savedCategory = categoryService.create(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping
  public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
    return ResponseEntity.ok(categoryService.update(category));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {

    categoryService.delete(id);
  }

}
