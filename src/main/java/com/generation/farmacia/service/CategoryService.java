package com.generation.farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Category;
import com.generation.farmacia.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Category> getAll() {
    return categoryRepository.findAll();
  }

  public Category getById(Long id) {
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada", null));
  }

  public List<Category> getByName(String name) {
    return categoryRepository.findAllByNameContainingIgnoreCase(name);
  }

  public List<Category> getByDescription(String description) {
    return categoryRepository.findAllByDescriptionContainingIgnoreCase(description);
  }

  public Category create(Category category) {
    return categoryRepository.save(category);
  }

  public Category update(Category category) {

    if (!categoryRepository.existsById(category.getId()))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");

    return categoryRepository.save(category);
  }

  public void delete(Long id) {

    if (!categoryRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!");
    }

    categoryRepository.deleteById(id);
  }

}
