package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.farmacia.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByNameContainingIgnoreCase(String name);

  List<Product> findAllByDescriptionContainingIgnoreCase(String description);

  List<Product> findAllByPrice(BigDecimal price);

  List<Product> findAllByPriceLessThanEqual(BigDecimal price);

  List<Product> findAllByPriceGreaterThanEqual(BigDecimal price);

}
