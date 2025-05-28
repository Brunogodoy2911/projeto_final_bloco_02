package com.generation.farmacia.controller;

import java.math.BigDecimal;
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

import com.generation.farmacia.model.Product;
import com.generation.farmacia.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getById(id));
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(productService.getByName(name));
  }

  @GetMapping("/description/{description}")
  public ResponseEntity<List<Product>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(productService.getByDescription(description));
  }

  @GetMapping("/price/{price}")
  public ResponseEntity<List<Product>> getByPrice(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPrice(price));
  }

  @GetMapping("/price/min/{price}")
  public ResponseEntity<List<Product>> getByPriceLessThan(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPriceLessThanEqual(price));
  }

  @GetMapping("/price/max/{price}")
  public ResponseEntity<List<Product>> getByPriceGreaterThan(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPriceGreaterThanEqual(price));
  }

  @PostMapping
  public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
  }

  @PutMapping
  public ResponseEntity<Product> update(@Valid @RequestBody Product product) {

    return ResponseEntity.ok(productService.update(product));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    productService.delete(id);
  }

}
