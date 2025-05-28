package com.generation.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "tb_categorias")
@Schema(name = "Category", description = "Representa uma categoria de produto na farmácia.")

public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identificador único da categoria", example = "1")
  private Long id;

  @Column(length = 100)
  @NotBlank(message = "O nome da categoria é obrigatório")
  @Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres!")
  @Schema(description = "Nome da categoria do produto (ex: 'Medicamentos', 'Higiene Pessoal')", example = "Medicamentos", minLength = 5, maxLength = 100) 
  private String name;

  @Column(length = 1000)
  @NotBlank(message = "A descrição da categoria é obrigatória")
  @Size(min = 10, max = 1000, message = "O atributo descrição deve ter no mínimo 10 e no máximo 1000 caracteres!")
  @Schema(description = "Descrição detalhada da categoria (ex: 'Produtos para saúde e tratamento de doenças')", example = "Produtos para saúde e tratamento de doenças", minLength = 10, maxLength = 1000)
  private String description;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
  @JsonIgnoreProperties("categories")
  @Schema(description = "Lista de produtos associados a esta categoria")
  private List<Product> products;

}