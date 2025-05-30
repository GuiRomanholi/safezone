package br.com.fiap.safezone.service;

import br.com.fiap.safezone.dto.AlertaRequest;
import br.com.fiap.safezone.dto.AlertaResponse;
import br.com.fiap.safezone.entity.Alerta;
import br.com.fiap.safezone.entity.Leitura;
import br.com.fiap.safezone.entity.Usuario;
import br.com.fiap.safezone.repository.AlertaRepository;
import br.com.fiap.safezone.repository.LeituraRepository;
import br.com.fiap.safezone.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AlertaResponse save(AlertaRequest dto) {
        Leitura leitura = leituraRepository.findById(dto.getLeituraId())
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Alerta alerta = new Alerta();
        alerta.setTipo(dto.getTipo());
        alerta.setDescricao(dto.getDesc());
        alerta.setDataHora(dto.getDataHora());
        alerta.setLeitura(leitura);
        alerta.setUsuario(usuario);

        alerta = alertaRepository.save(alerta);
        return toResponse(alerta);
    }

    public Page<AlertaResponse> findAll(Pageable pageable) {
        return alertaRepository.findAll(pageable).map(this::toResponse);
    }

    public AlertaResponse findById(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
        return toResponse(alerta);
    }

    public AlertaResponse update(Long id, AlertaRequest dto) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));

        Leitura leitura = leituraRepository.findById(dto.getLeituraId())
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        alerta.setTipo(dto.getTipo());
        alerta.setDescricao(dto.getDesc());
        alerta.setDataHora(dto.getDataHora());
        alerta.setLeitura(leitura);
        alerta.setUsuario(usuario);

        alerta = alertaRepository.save(alerta);
        return toResponse(alerta);
    }

    public void delete(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado"));
        alertaRepository.delete(alerta);
    }

    private AlertaResponse toResponse(Alerta alerta) {
        return new AlertaResponse(
                alerta.getId(),
                alerta.getTipo(),
                alerta.getDescricao(),
                alerta.getDataHora(),
                alerta.getLeitura().getId(),
                alerta.getUsuario().getId()
        );
    }
}
