package br.com.fiap.safezone.dto;

import br.com.fiap.safezone.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank String email,
        @NotBlank String senha,
        @NotNull UserRole role
) {
}
