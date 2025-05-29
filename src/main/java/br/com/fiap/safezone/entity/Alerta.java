package br.com.fiap.safezone.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "alerta_seq")
    @SequenceGenerator(name = "alerta_seq", sequenceName = "alerta_seq", allocationSize = 1)
    private Long id;

    private String tipo;

    private String descricao;

    private LocalDateTime dataHora;

    @ManyToOne(optional = false)
    @JoinColumn(name = "leitura_id_lei")
    private Leitura leitura;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id_usu")
    private Usuario usuario;

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Leitura getLeitura() {
        return leitura;
    }

    public void setLeitura(Leitura leitura) {
        this.leitura = leitura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
