package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.AlertaRequest;
import br.com.fiap.safezone.dto.AlertaResponse;
import br.com.fiap.safezone.service.AlertaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/alertas", produces = {"application/json"})
@Tag(name = "api-alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @PostMapping
    public ResponseEntity<AlertaResponse> create(@RequestBody @Valid AlertaRequest dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> update(@PathVariable Long id, @RequestBody @Valid AlertaRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
