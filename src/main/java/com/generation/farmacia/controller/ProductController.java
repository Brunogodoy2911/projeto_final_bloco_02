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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Produtos", description = "Informações dos Produtos")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  @Operation(summary = "Lista todos os produtos", description = "Recupera uma lista completa de todos os produtos cadastrados na farmácia.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de produtos recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getAll() {
    return ResponseEntity.ok(productService.getAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Busca produto por ID", description = "Recupera os detalhes de um produto específico usando seu ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Produto não encontrado para o ID fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    return ResponseEntity.ok(productService.getById(id));
  }

  @GetMapping("/name/{name}")
  @Operation(summary = "Busca produtos por nome", description = "Recupera uma lista de produtos que contêm o nome especificado (busca parcial).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o nome fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(productService.getByName(name));
  }

  @GetMapping("/description/{description}")
  @Operation(summary = "Busca produtos por descrição", description = "Recupera uma lista de produtos cuja descrição contém o texto especificado (busca parcial).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com a descrição fornecida")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(productService.getByDescription(description));
  }

  @GetMapping("/price/{price}")
  @Operation(summary = "Busca produtos por preço exato", description = "Recupera uma lista de produtos que possuem o preço exato especificado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado com o preço exato fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getByPrice(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPrice(price));
  }

  @GetMapping("/price/min/{price}")
  @Operation(summary = "Busca produtos com preço até um valor máximo", description = "Recupera uma lista de produtos cujo preço é menor ou igual ao valor especificado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado abaixo ou igual ao preço mínimo fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getByPriceLessThan(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPriceLessThanEqual(price));
  }

  @GetMapping("/price/max/{price}")
  @Operation(summary = "Busca produtos com preço a partir de um valor mínimo", description = "Recupera uma lista de produtos cujo preço é maior ou igual ao valor especificado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado acima ou igual ao preço máximo fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Product>> getByPriceGreaterThan(@PathVariable BigDecimal price) {
    return ResponseEntity.ok(productService.getByPriceGreaterThanEqual(price));
  }

  @PostMapping
  @Operation(summary = "Cria um novo produto", description = "Cadastra um novo produto no sistema da farmácia. O produto requer nome, preço, etc.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "400", description = "Dados do produto inválidos ou obrigatórios faltando")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
  }

  @PutMapping
  @Operation(summary = "Atualiza um produto existente", description = "Atualiza os dados de um produto existente com base no ID fornecido no corpo da requisição. Todos os campos são passíveis de atualização.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))),
      @ApiResponse(responseCode = "400", description = "Dados do produto inválidos ou ID não encontrado"),
      @ApiResponse(responseCode = "404", description = "Produto não encontrado para atualização")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Product> update(@Valid @RequestBody Product product) {
    return ResponseEntity.ok(productService.update(product));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deleta um produto por ID", description = "Remove um produto do sistema da farmácia utilizando seu ID. Esta ação é irreversível.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
      @ApiResponse(responseCode = "404", description = "Produto não encontrado para deleção")
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @SecurityRequirement(name = "jwt_auth")
  public void delete(@PathVariable Long id) {
    productService.delete(id);
  }

}