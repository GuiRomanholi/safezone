package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.AlertaRequest;
import br.com.fiap.safezone.dto.AlertaResponse;
import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/alertas", produces = {"application/json"})
@Tag(name = "api-alertas" , description = "CRUD de Alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @Operation(summary = "Criar um novo Alerta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<AlertaResponse> create(@RequestBody @Valid AlertaRequest dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "Retorna uma lista de Alertas")
    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Operation(summary = "Retorna um alerta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DispositivoResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum Alerta encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Atualiza um alerta existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta encontrado e atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Nenhum alerta encontrado para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> update(@PathVariable Long id, @RequestBody @Valid AlertaRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Exclui um alerta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhum alerta encontrado para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Alerta excluído com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
