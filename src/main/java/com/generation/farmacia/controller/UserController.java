package com.generation.farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.User;
import com.generation.farmacia.model.UserLogin;
import com.generation.farmacia.repository.UserRepository;
import com.generation.farmacia.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Usuários", description = "Gerenciamento de Usuários da Farmácia")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @GetMapping("/all")
  @Operation(summary = "Lista todos os usuários", description = "Recupera uma lista completa de todos os usuários cadastrados no sistema da farmácia. Requer autenticação JWT.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "401", description = "Não autorizado, token JWT ausente ou inválido"),
      @ApiResponse(responseCode = "403", description = "Acesso proibido, o usuário não tem permissão"),
      @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<List<User>> getAll() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Busca usuário por ID", description = "Recupera os detalhes de um usuário específico usando seu ID. Requer autenticação JWT.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "401", description = "Não autorizado, token JWT ausente ou inválido"),
      @ApiResponse(responseCode = "403", description = "Acesso proibido, o usuário não tem permissão"),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrado para o ID fornecido")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    return userRepository.findById(id)
        .map(resposta -> ResponseEntity.ok(resposta))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/login")
  @Operation(summary = "Autentica um usuário", description = "Realiza o login de um usuário com suas credenciais (email e senha) e retorna um token JWT para acesso autorizado.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login bem-sucedido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserLogin.class))),
      @ApiResponse(responseCode = "401", description = "Credenciais inválidas (email ou senha incorretos)")
  })
  public ResponseEntity<UserLogin> authenticateUser(@RequestBody Optional<UserLogin> usuarioLogin) {
    return userService.authenticateUser(usuarioLogin)
        .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
        .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }

  @PostMapping("/register")
  @Operation(summary = "Cadastra um novo usuário", description = "Registra um novo usuário no sistema da farmácia. O email deve ser único e a senha será criptografada.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Dados do usuário inválidos ou email já cadastrado")
  })
  public ResponseEntity<User> postUser(@RequestBody @Valid User user) {
    return userService.registerUser(user)
        .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
        .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }

  @PutMapping("/update")
  @Operation(summary = "Atualiza os dados de um usuário", description = "Atualiza as informações de perfil de um usuário existente. O ID do usuário deve ser fornecido no corpo da requisição. Requer autenticação JWT.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
      @ApiResponse(responseCode = "400", description = "Dados do usuário inválidos ou ID ausente/inválido"),
      @ApiResponse(responseCode = "401", description = "Não autorizado, token JWT ausente ou inválido"),
      @ApiResponse(responseCode = "403", description = "Acesso proibido, o usuário não tem permissão para atualizar este perfil"),
      @ApiResponse(responseCode = "404", description = "Usuário não encontrado para atualização")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<User> putUser(@Valid @RequestBody User user) {
    return userService.updateUser(user)
        .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
  }
}