package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoUs(String correoUs);
    Optional<Usuario> findByCorreoUsIgnoreCase(String correoUs);
    boolean existsByCorreoUsIgnoreCase(String correoUs);
    List<Usuario> findByEstadoActivoUsTrue();
    List<Usuario> findByRolUsIgnoreCase(String rolUs);
}
