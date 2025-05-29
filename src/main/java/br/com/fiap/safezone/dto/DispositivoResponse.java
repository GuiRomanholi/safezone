package br.com.fiap.safezone.dto;

public record DispositivoResponse(Long id, Double latitude, Double longitude, String localDesc, Boolean ativo) {
}
