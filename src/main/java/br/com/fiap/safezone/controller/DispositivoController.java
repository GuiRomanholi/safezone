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
import org.springdoc.core.annotations.ParameterObject;
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

    @Autowired
    private DispositivoService service;

    @Operation(summary = "Criar um novo Dispositivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispositivo cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<DispositivoResponse> create(@RequestBody @Valid DispositivoRequest dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @Operation(summary = "Retorna uma lista de Dispositivos")
    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @Operation(summary = "Retorna um dispositivo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DispositivoResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum Dispositivo encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponse> getById(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um dispositivo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispositivo encontrado e atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Nenhum dispositivo encontrado para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<DispositivoResponse> update(@PathVariable Long id, @RequestBody @Valid DispositivoRequest dto) {
        return service.atualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Exclui um dispositivo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhum dispositivo encontrado para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Dispositivo excluído com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
