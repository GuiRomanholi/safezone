package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.dto.LeituraRequest;
import br.com.fiap.safezone.dto.LeituraResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.service.LeituraService;
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
@RequestMapping(value = "/leituras", produces = {"application/json"})
@Tag(name = "api-leituras" , description = "CRUD de Leituras")
public class LeituraController {

    @Autowired
    private LeituraService service;

    @Operation(summary = "Criar uma nova Leitura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispositivo cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<LeituraResponse> create(@RequestBody @Valid LeituraRequest dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @Operation(summary = "Retorna uma lista de Leituras")
    @GetMapping
    public ResponseEntity<?> getAll(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @Operation(summary = "Retorna uma leitura por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leitura encontrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DispositivoResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhuma leitura encontrada",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<LeituraResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Atualiza uma leitura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Leituras encontradas e atualizadas com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Nenhuma leitura encontrada para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<LeituraResponse> update(@PathVariable Long id, @RequestBody @Valid LeituraRequest dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Exclui uma leitura por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhuma leitura encontrada para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Leitura excluída com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
