package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.DispositivoRequest;
import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.repository.DispositivoRepository;
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
@Tag(name = "api-dispositivos")
public class DispositivoController {

    /*@Autowired
    private DispositivoRepository dispositivoRepository;

    @Autowired
    private DispositivoService dispositivoService;

    @Operation(summary = "Cria um novo dispositivo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispositivo cadastrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Atributos informados são inválidos",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping
    public ResponseEntity<Dispositivo> createDispositivo(@Valid @RequestBody DispositivoRequest request) {
        Dispositivo dispositivoSalvo = dispositivoRepository.save(dispositivoService.requestToEntity(request));
        return new ResponseEntity<>(dispositivoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna uma lista de dispositivos")
    @GetMapping
    public ResponseEntity<Page<DispositivoResponse>> readDispositivos(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));
        return new ResponseEntity<>(dispositivoService.findAll(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Retorna um dispositivo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispositivo encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DispositivoResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum dispositivo encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<DispositivoResponse> readDispositivo(@PathVariable Long id) {
        Optional<Dispositivo> dispositivo = dispositivoRepository.findById(id);
        if (dispositivo.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dispositivoService.entityToResponse(dispositivo.get()), HttpStatus.OK);
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
    public ResponseEntity<Dispositivo> updateDispositivo(@PathVariable Long id, @RequestBody Dispositivo dispositivo) {
        Optional<Dispositivo> existente = dispositivoRepository.findById(id);
        if (existente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dispositivo.setId(existente.get().getId());
        Dispositivo atualizado = dispositivoRepository.save(dispositivo);
        return new ResponseEntity<>(atualizado, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um dispositivo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhum dispositivo encontrado para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Dispositivo excluído com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable Long id) {
        Optional<Dispositivo> existente = dispositivoRepository.findById(id);
        if (existente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        dispositivoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
