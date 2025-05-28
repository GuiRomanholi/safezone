package br.com.fiap.safezone.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank String email,
        @NotBlank String senha
) {
}
