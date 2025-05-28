package com.generation.farmacia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Product;
import com.generation.farmacia.repository.CategoryRepository;
import com.generation.farmacia.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  public List<Product> getAll() {
    return productRepository.findAll();
  }

  public Product getById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
  }

  public List<Product> getByName(String name) {
    return productRepository.findAllByNameContainingIgnoreCase(name);
  }

  public List<Product> getByDescription(String description) {
    return productRepository.findAllByDescriptionContainingIgnoreCase(description);
  }

  public List<Product> getByPrice(BigDecimal price) {
    return productRepository.findAllByPrice(price);
  }

  public List<Product> getByPriceLessThanEqual(BigDecimal price) {
    return productRepository.findAllByPriceLessThanEqual(price);
  }

  public List<Product> getByPriceGreaterThanEqual(BigDecimal price) {
    return productRepository.findAllByPriceGreaterThanEqual(price);
  }

  public Product create(Product product) {
    if (!categoryRepository.existsById(product.getCategory().getId()))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada", null);

    return productRepository.save(product);
  }

  public Product update(Product product) {
    if (!productRepository.existsById(product.getId()))
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");

    if (!categoryRepository.existsById(product.getCategory().getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não encontrada!");
    }

    return productRepository.save(product);
  }

  public void delete(Long id) {
    if (!productRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    productRepository.deleteById(id);
  }
}
