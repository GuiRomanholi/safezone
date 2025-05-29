package br.com.fiap.safezone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class AlertaRequest {

    @NotBlank
    @Size(max = 30)
    private String tipo;

    @Size(max = 30)
    private String desc;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private Long leituraId;

    @NotNull
    private Long usuarioId;

    public AlertaRequest() {
    }

    public AlertaRequest(String tipo, String desc, LocalDateTime dataHora, Long leituraId, Long usuarioId) {
        this.tipo = tipo;
        this.desc = desc;
        this.dataHora = dataHora;
        this.leituraId = leituraId;
        this.usuarioId = usuarioId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getLeituraId() {
        return leituraId;
    }

    public void setLeituraId(Long leituraId) {
        this.leituraId = leituraId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
