package br.com.fiap.safezone.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank String nome,
        @NotBlank String senha,
        @NotBlank String email
) {
}
