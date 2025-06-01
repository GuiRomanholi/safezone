package br.com.fiap.safezone.controller;

import br.com.fiap.safezone.dto.DispositivoResponse;
import br.com.fiap.safezone.dto.UsuarioRequest;
import br.com.fiap.safezone.entity.Dispositivo;
import br.com.fiap.safezone.entity.UserRole;
import br.com.fiap.safezone.entity.Usuario;
import br.com.fiap.safezone.repository.UsuarioRepository;
import br.com.fiap.safezone.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
@Tag(name = "api-usuarios", description = "CRUD de Usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Retorna uma lista de Usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @Operation(summary = "Retorna um usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DispositivoResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Nenhum usuario encontrado",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza um usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario encontrado e atualizado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Dispositivo.class))}),
            @ApiResponse(responseCode = "400", description = "Nenhum usuario encontrado para atualizar",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid UsuarioRequest request) {
        Optional<Usuario> optional = usuarioRepository.findById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();

        Usuario usuarioExistente = optional.get();

        // Atualiza os campos com os dados do request
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setSenha(request.getSenha()); // A criptografia será feita no service
        usuarioExistente.setRole(UserRole.valueOf(request.getRole().toUpperCase()));

        // Salva via service, que cuida do cache e da criptografia
        Usuario atualizado = usuarioService.updateUsuario(id, usuarioExistente);

        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Exclui um usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Nenhum usuario encontrado para excluir",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) return ResponseEntity.notFound().build();
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

