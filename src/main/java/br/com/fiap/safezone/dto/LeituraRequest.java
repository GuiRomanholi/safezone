package br.com.fiap.safezone.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class LeituraRequest {
    @NotNull(message = "A data é obrigatória")
    private LocalDateTime dataHora;

    @NotNull(message = "A temperatura é obrigatória")
    private Double temperatura;

    @NotNull(message = "A umidade é obrigatória")
    private Double umidade;

    @NotNull
    private Long dispositivoId;

    public LeituraRequest() {
    }
    public LeituraRequest(LocalDateTime dataHora, Double temperatura, Double umidade, Long dispositivoId) {
        this.dataHora = dataHora;
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.dispositivoId = dispositivoId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getUmidade() {
        return umidade;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public Long getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(Long dispositivoId) {
        this.dispositivoId = dispositivoId;
    }
}
