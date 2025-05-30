package br.com.fiap.safezone.service;

import br.com.fiap.safezone.dto.LeituraRequest;
import br.com.fiap.safezone.dto.LeituraResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.entity.Leitura;
import br.com.fiap.safezone.repository.DispositivoRepository;
import br.com.fiap.safezone.repository.LeituraRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LeituraService {

    @Autowired
    private LeituraRepository leituraRepository;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    public LeituraResponse save(LeituraRequest dto) {
        Dispositivo dispositivo = dispositivoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        Leitura leitura = new Leitura();
        leitura.setDataHora(dto.getDataHora());
        leitura.setTemperatura(dto.getTemperatura());
        leitura.setUmidade(dto.getUmidade());
        leitura.setDispositivo(dispositivo);

        leitura = leituraRepository.save(leitura);
        return toResponse(leitura);
    }

    public Page<LeituraResponse> findAll(Pageable pageable) {
        return leituraRepository.findAll(pageable).map(this::toResponse);
    }

    public LeituraResponse findById(Long id) {
        Leitura leitura = leituraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));
        return toResponse(leitura);
    }

    public LeituraResponse update(Long id, LeituraRequest dto) {
        Leitura leitura = leituraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        Dispositivo dispositivo = dispositivoRepository.findById(dto.getDispositivoId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositivo não encontrado"));

        leitura.setDataHora(dto.getDataHora());
        leitura.setTemperatura(dto.getTemperatura());
        leitura.setUmidade(dto.getUmidade());
        leitura.setDispositivo(dispositivo);

        leitura = leituraRepository.save(leitura);
        return toResponse(leitura);
    }

    public void delete(Long id) {
        Leitura leitura = leituraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));
        leituraRepository.delete(leitura);
    }

    private LeituraResponse toResponse(Leitura leitura) {
        return new LeituraResponse(
                leitura.getId(),
                leitura.getDataHora(),
                leitura.getTemperatura(),
                leitura.getUmidade(),
                leitura.getDispositivo().getId()
        );
    }
}
