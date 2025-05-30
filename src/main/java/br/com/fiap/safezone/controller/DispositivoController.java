package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.DispositivoRequest;
import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.repository.DispositivoRepository;
import br.com.fiap.safezone.service.DispositivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/dispositivos", produces = {"application/json"})
@Tag(name = "api-dispositivos", description = "CRUD de Dispositivos")
public class DispositivoController {

    private final DispositivoService service;

    public DispositivoController(DispositivoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DispositivoResponse> cadastrar(@RequestBody @Valid DispositivoRequest request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @GetMapping
    public ResponseEntity<Page<DispositivoResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid DispositivoRequest request) {
        return service.atualizar(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
