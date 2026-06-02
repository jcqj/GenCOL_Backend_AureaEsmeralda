package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.AuthResponse;
import com.aureaesmeralda.AureaEsmeralda.DTO.LoginRequest;
import com.aureaesmeralda.AureaEsmeralda.DTO.RegistroRequest;
import com.aureaesmeralda.AureaEsmeralda.DTO.UsuarioDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import com.aureaesmeralda.AureaEsmeralda.security.JwtUtil;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil,
                       AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    // 1. Registro de un nuevo usuario
    @Transactional
    public AuthResponse registrar(RegistroRequest request) {
        // Verificar si el correo ya existe
        if (usuarioRepository.existsByCorreoUs(request.getCorreoUs())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }

        // Crear el nuevo usuario
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombreUs(request.getNombreUs());
        nuevoUsuario.setTelefonoUs(request.getTelefonoUs());
        nuevoUsuario.setCorreoUs(request.getCorreoUs());
        
        // Encriptar la contraseña
        nuevoUsuario.setContrasenaUs(passwordEncoder.encode(request.getContrasenaUs()));
        
        // El registro publico siempre crea clientes. Los roles elevados se gestionan aparte.
        nuevoUsuario.setRolUs("CLIENTE");
        
        nuevoUsuario.setEstadoActivoUs(true);

        // Guardar en base de datos
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // Generar UserDetails de Spring Security para el token
        String userRole = usuarioGuardado.getRolUs();
        if (!userRole.toUpperCase().startsWith("ROLE_")) {
            userRole = "ROLE_" + userRole.toUpperCase();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(usuarioGuardado.getCorreoUs())
                .password(usuarioGuardado.getContrasenaUs())
                .authorities(new SimpleGrantedAuthority(userRole))
                .build();

        // Generar token JWT
        String token = jwtUtil.generateToken(userDetails);

        UsuarioDTO usuarioDTO = MapperUtil.toUsuarioDTO(usuarioGuardado);
        return new AuthResponse(token, usuarioDTO);
    }

    // 2. Inicio de sesión
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest request) {
        // Autenticar credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoUs(),
                        request.getContrasenaUs()
                )
        );

        // Cargar detalles del usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Buscar usuario en base de datos para mapearlo al DTO
        Usuario usuario = usuarioRepository.findByCorreoUs(request.getCorreoUs())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Generar token JWT
        String token = jwtUtil.generateToken(userDetails);

        UsuarioDTO usuarioDTO = MapperUtil.toUsuarioDTO(usuario);
        return new AuthResponse(token, usuarioDTO);
    }
}
