package com.generation.farmacia.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema; // Import for @Schema

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_produtos")
@Schema(name = "Product", description = "Representa um produto disponível na farmácia.")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Identificador único do produto", example = "101")
  private Long id;

  @Column(length = 100)
  @NotBlank(message = "O nome do produto é obrigatório")
  @Size(min = 5, max = 100, message = "O atributo nome deve ter no mínimo 5 e no máximo 100 caracteres!")
  @Schema(description = "Nome do produto (ex: 'Paracetamol 500mg', 'Shampoo Anticaspa')", example = "Dipirona 1g", minLength = 5, maxLength = 100)
  private String name;

  @Column(length = 1000)
  @NotBlank(message = "A descrição do produto é obrigatória")
  @Size(min = 10, max = 1000, message = "O atributo descrição deve ter no mínimo 10 e no máximo 1000 caracteres!")
  @Schema(description = "Descrição detalhada do produto e suas características (ex: 'Analgésico e antitérmico para dores e febre')", example = "Medicamento para alívio de dores leves a moderadas e febre. Uso oral.", minLength = 10, maxLength = 1000)
  private String description;

  @Column(precision = 10, scale = 2)
  @NotNull(message = "O preço do produto é obrigatório")
  @DecimalMin(value = "0.00", inclusive = false, message = "O preço deve ser maior que zero")
  @Digits(integer = 8, fraction = 2, message = "O preço deve ter no máximo 8 dígitos inteiros e 2 decimais")
  @Schema(description = "Preço do produto em reais (com duas casas decimais)", example = "19.99", format = "float")
  private BigDecimal price;

  @Column(length = 5000)
  @Schema(description = "URL da imagem do produto", example = "https://example.com/imagens/paracetamol.jpg", nullable = true)
  private String image;

  @UpdateTimestamp
  @Schema(description = "Data e hora da última atualização do registro do produto", example = "2024-05-28T14:30:00", readOnly = true)
  private LocalDateTime date;

  @ManyToOne
  @JsonIgnoreProperties("products")
  @Schema(description = "Categoria à qual o produto pertence")
  private Category category;
}