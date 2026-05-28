package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.UsuarioDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository repositorioUsuario;
    private final PasswordEncoder codificadorContrasena;

    public UsuarioService(UsuarioRepository repositorioUsuario, PasswordEncoder codificadorContrasena) {
        this.repositorioUsuario = repositorioUsuario;
        this.codificadorContrasena = codificadorContrasena;
    }

    // POST /api/usuarios/registro
    @Transactional
    public UsuarioDTO registrar(Usuario datos) {

        // 1. Verificar que el correo no esté ya registrado
        if (repositorioUsuario.existsByCorreoUs(datos.getCorreoUs())) {
            throw new IllegalArgumentException("Ya existe un usuario con el correo: " + datos.getCorreoUs());
        }

        // 2. Cifrar la contraseña antes de guardar — NUNCA guardar texto plano
        datos.setContrasenaUs(codificadorContrasena.encode(datos.getContrasenaUs()));

        // 3. Asegurar valores por defecto si no vienen en el cuerpo de la petición
        if (datos.getRolUs() == null || datos.getRolUs().isBlank()) {
            datos.setRolUs("Usuario");
        }
        if (datos.getEstadoActivoUs() == null) {
            datos.setEstadoActivoUs(true);
        }

        Usuario usuarioGuardado = repositorioUsuario.save(datos);
        return MapperUtil.toUsuarioDTO(usuarioGuardado);
    }

    // POST /api/usuarios/login
    @Transactional(readOnly = true)
    public UsuarioDTO iniciarSesion(String correo, String contrasena) {

        // 1. Buscar el usuario por correo
        Usuario usuario = repositorioUsuario.findByCorreoUs(correo)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // 2. Verificar que la cuenta esté activa
        if (!usuario.getEstadoActivoUs()) {
            throw new RuntimeException("La cuenta está desactivada");
        }

        // 3. Comparar la contraseña ingresada con el cifrado guardado
        if (!codificadorContrasena.matches(contrasena, usuario.getContrasenaUs())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return MapperUtil.toUsuarioDTO(usuario);
    }

    // GET /api/usuarios/{id}
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = repositorioUsuario.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
        return MapperUtil.toUsuarioDTO(usuario);
    }
}