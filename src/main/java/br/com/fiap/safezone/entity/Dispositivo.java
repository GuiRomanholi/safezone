package br.com.fiap.safezone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;

    private String localDesc;

    private Boolean ativo;

    @OneToMany(mappedBy = "dispositivo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Leitura> leituras;

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Leitura> getLeituras() {
        return leituras;
    }

    public void setLeituras(List<Leitura> leituras) {
        this.leituras = leituras;
    }
}



