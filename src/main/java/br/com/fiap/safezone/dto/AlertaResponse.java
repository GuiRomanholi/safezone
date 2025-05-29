package br.com.fiap.safezone.dto;

import java.time.LocalDateTime;

public record AlertaResponse(Long id, String tipo, String desc, LocalDateTime dataHora, Long leituraId, Long usuarioId) {
}
