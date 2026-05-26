package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByCorreoUsIgnoreCase(usuario.getCorreoUs())) {
            throw new IllegalArgumentException("El correo ya está registrado por otro usuario.");
        }

        // Encriptar la contraseña
        String contrasenaEncriptada = passwordEncoder.encode(usuario.getContrasenaUs());
        usuario.setContrasenaUs(contrasenaEncriptada);

        // Guardar en la base de datos
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> obtenerActivos() {
        return usuarioRepository.findByEstadoActivoUsTrue();
    }

    public List<Usuario> obtenerPorRol(String rol) {
        return usuarioRepository.findByRolUsIgnoreCase(rol);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorCorreo(String correo) {
        return usuarioRepository.findByCorreoUsIgnoreCase(correo);
    }

    public Optional<Usuario> autenticar(String correo, String contrasenaPlana) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoUsIgnoreCase(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (passwordEncoder.matches(contrasenaPlana, usuario.getContrasenaUs())) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Long id, Usuario usuarioDetalles) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el ID " + id + " no existe."));

        usuario.setNombreUs(usuarioDetalles.getNombreUs());
        usuario.setTelefonoUs(usuarioDetalles.getTelefonoUs());
        
        if (!usuario.getCorreoUs().equalsIgnoreCase(usuarioDetalles.getCorreoUs())) {
            if (usuarioRepository.existsByCorreoUsIgnoreCase(usuarioDetalles.getCorreoUs())) {
                throw new IllegalArgumentException("El correo ingresado ya está registrado por otro usuario.");
            }
            usuario.setCorreoUs(usuarioDetalles.getCorreoUs());
        }

        // Si se proporciona una nueva contraseña y no está vacía, se encripta y actualiza
        if (usuarioDetalles.getContrasenaUs() != null && !usuarioDetalles.getContrasenaUs().isBlank()) {
            String contrasenaEncriptada = passwordEncoder.encode(usuarioDetalles.getContrasenaUs());
            usuario.setContrasenaUs(contrasenaEncriptada);
        }

        usuario.setEstadoActivoUs(usuarioDetalles.getEstadoActivoUs());
        usuario.setRolUs(usuarioDetalles.getRolUs());

        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el ID " + id + " no existe."));
        usuarioRepository.delete(usuario);
    }
}
