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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Categorias", description = "Informações das Categorias de Produtos")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  @Operation(summary = "Lista todas as categorias", description = "Recupera uma lista completa de todas as categorias de produtos cadastradas na farmácia.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de categorias recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Category>> getAll() {
    return ResponseEntity.ok(categoryService.getAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Busca categoria por ID", description = "Recupera os detalhes de uma categoria específica usando seu ID.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "404", description = "Categoria não encontrada para o ID fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Category> getById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getById(id));
  }

  @GetMapping("/name/{name}")
  @Operation(summary = "Busca categorias por nome", description = "Recupera uma lista de categorias que contêm o nome especificado (busca parcial).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada com o nome fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Category>> getByName(@PathVariable String name) {
    return ResponseEntity.ok(categoryService.getByName(name));
  }

  @GetMapping("/description/{description}")
  @Operation(summary = "Busca categorias por descrição", description = "Recupera uma lista de categorias cuja descrição contém o texto especificado (busca parcial).")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Categorias encontradas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "404", description = "Nenhuma categoria encontrada com a descrição fornecida")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<Category>> getByDescription(@PathVariable String description) {
    return ResponseEntity.ok(categoryService.getByDescription(description));
  }

  @PostMapping
  @Operation(summary = "Cria uma nova categoria", description = "Cadastra uma nova categoria de produto no sistema. A categoria requer um nome e uma descrição.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "400", description = "Dados da categoria inválidos ou obrigatórios faltando")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Category> create(@Valid @RequestBody Category category) {
    Category savedCategory = categoryService.create(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping
  @Operation(summary = "Atualiza uma categoria existente", description = "Atualiza os dados de uma categoria existente com base no ID fornecido no corpo da requisição. Todos os campos são passíveis de atualização.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))),
      @ApiResponse(responseCode = "400", description = "Dados da categoria inválidos ou ID não encontrado"),
      @ApiResponse(responseCode = "404", description = "Categoria não encontrada para atualização")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Category> update(@Valid @RequestBody Category category) {
    return ResponseEntity.ok(categoryService.update(category));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Deleta uma categoria por ID", description = "Remove uma categoria do sistema utilizando seu ID. Esta ação é irreversível.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
      @ApiResponse(responseCode = "404", description = "Categoria não encontrada para deleção")
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @SecurityRequirement(name = "jwt_auth")
  public void delete(@PathVariable Long id) {
    categoryService.delete(id);
  }

}