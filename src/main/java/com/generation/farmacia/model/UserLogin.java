package com.generation.farmacia.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLogin {

  private Long id;
  private String name;

  @Schema(description = "Username para login", example = "brunogodoy123@email.com")
  private String username;

  @Schema(description = "Senha para login", example = "123456")
  private String password;

  private String photo;
  private String token;
}