package br.com.fiap.safezone.service;

import br.com.fiap.safezone.entity.Usuario;
import br.com.fiap.safezone.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @CachePut(value = "usuarios", key = "#result.id")
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public Usuario readUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "usuarios", key = "'todos'")
    public List<Usuario> readUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    @CacheEvict(value = "usuarios", allEntries = true)
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return null;
        }

        Usuario usuarioExistente = usuarioOptional.get();

        usuario.setId(id);

        if (!usuario.getSenha().equals(usuarioExistente.getSenha())) {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    @CacheEvict(value = "usuarios", key = "#id")
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
        limparCacheTodosUsuarios();
    }

    @CacheEvict(value = "usuarios", key = "'todos'")
    public void limparCacheTodosUsuarios() {}

    @CacheEvict(value = "usuarios", allEntries = true)
    public void limparTodoCacheUsuarios() {}

}
