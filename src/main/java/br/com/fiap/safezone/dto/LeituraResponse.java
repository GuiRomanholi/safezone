package br.com.fiap.safezone.dto;

import java.time.LocalDateTime;

public record LeituraResponse(Long id, LocalDateTime dataHora, Double temperatura, Double umidade, Long dispositivoId) {
}
