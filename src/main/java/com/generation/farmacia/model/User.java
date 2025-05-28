package com.generation.farmacia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_users")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "O nome do usuário é obrigatório")
  @Schema(example = "Bruno Godoy", requiredMode = RequiredMode.REQUIRED, description = "Nome do usuário")
  private String name;

  @NotBlank(message = "O Atributo Usuário é Obrigatório!")
  @Email(message = "O Atributo Usuário deve ser um email válido!")
  @Schema(example = "email@email.com.br", requiredMode = RequiredMode.REQUIRED, description = "Email do usuário")
  private String username;

  @NotBlank(message = "A senha do usuário é obrigatória")
  @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
  @Schema(example = "123456", requiredMode = RequiredMode.REQUIRED, description = "Senha do usuário")
  private String password;

  @Size(max = 5000, message = "O atributo imagem deve ter no máximo 5000 caracteres")
  @Schema(example = "http://imageurl.com", description = "Foto do usuário")
  private String photo;

}
