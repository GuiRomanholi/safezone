package br.com.fiap.safezone.service;

import br.com.fiap.safezone.dto.DispositivoRequest;
import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.repository.DispositivoRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DispositivoService {

    private final DispositivoRepository repository;

    public DispositivoService(DispositivoRepository repository) {
        this.repository = repository;
    }

    public DispositivoResponse salvar(DispositivoRequest dto) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setLatitude(dto.getLatitude());
        dispositivo.setLongitude(dto.getLongitude());
        dispositivo.setLocalDesc(dto.getLocalDesc());
        dispositivo.setAtivo(dto.getAtivo());

        Dispositivo salvo = repository.save(dispositivo);
        return new DispositivoResponse(salvo.getId(), salvo.getLatitude(), salvo.getLongitude(), salvo.getLocalDesc(), salvo.getAtivo());
    }

    public Page<DispositivoResponse> listar(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    public Optional<DispositivoResponse> buscarPorId(Long id) {
        return repository.findById(id).map(this::toResponse);
    }

    public Optional<DispositivoResponse> atualizar(Long id, DispositivoRequest dto) {
        return repository.findById(id).map(dispositivo -> {
            dispositivo.setLatitude(dto.getLatitude());
            dispositivo.setLongitude(dto.getLongitude());
            dispositivo.setLocalDesc(dto.getLocalDesc());
            dispositivo.setAtivo(dto.getAtivo());
            return toResponse(repository.save(dispositivo));
        });
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private DispositivoResponse toResponse(Dispositivo d) {
        return new DispositivoResponse(d.getId(), d.getLatitude(), d.getLongitude(), d.getLocalDesc(), d.getAtivo());
    }
}
