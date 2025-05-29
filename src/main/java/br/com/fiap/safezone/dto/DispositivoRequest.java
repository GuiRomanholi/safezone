package br.com.fiap.safezone.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DispositivoRequest {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @Size(max = 100)
    private String localDesc;

    @NotNull
    private Boolean ativo;

    public DispositivoRequest() {
    }

    public DispositivoRequest(Double latitude, Double longitude, String localDesc, Boolean ativo) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.localDesc = localDesc;
        this.ativo = ativo;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocalDesc() {
        return localDesc;
    }

    public void setLocalDesc(String localDesc) {
        this.localDesc = localDesc;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
