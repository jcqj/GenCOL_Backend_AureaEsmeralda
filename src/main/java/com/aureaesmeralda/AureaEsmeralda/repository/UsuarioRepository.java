package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    // Buscar un usuario por su correo electrónico
    Optional<Usuario> findByCorreoUs(String correoUs);

    // Verificar si ya existe un usuario registrado con ese correo
    Boolean existsByCorreoUs(String correoUs);
}
