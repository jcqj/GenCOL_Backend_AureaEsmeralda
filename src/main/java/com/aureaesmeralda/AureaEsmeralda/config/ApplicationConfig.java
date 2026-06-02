package com.aureaesmeralda.AureaEsmeralda.config;


import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class ApplicationConfig {

    private final UsuarioRepository usuarioRepository;

    // Inyección del repositorio de usuarios mediante constructor nativo
    public ApplicationConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * 1. Definición del UserDetailsService usando una expresión Lambda.
     * Conecta la autenticación de Spring con tu tabla 'usuarios' en PostgreSQL.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository
                .findByCorreoUs(username)
                .map(usuario -> {
                    String role = usuario.getRolUs();
                    if (!role.toUpperCase().startsWith("ROLE_")) {
                        role = "ROLE_" + role.toUpperCase();
                    }
                    return new User(
                            usuario.getCorreoUs(),
                            usuario.getContrasenaUs(),
                            List.of(new SimpleGrantedAuthority(role))
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username
                ));
    }
    /**
     * 2. Componente encargado de coordinar la búsqueda del usuario y la validación de la contraseña.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * 3. Bean para la encriptación de contraseñas utilizando el algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 4. Administrador de autenticación nativo de Spring, necesario para procesar el Login en el controlador.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}