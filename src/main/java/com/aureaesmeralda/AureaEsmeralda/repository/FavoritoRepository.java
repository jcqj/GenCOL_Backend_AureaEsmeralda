package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Favorito;
<<<<<<< HEAD
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuario(Usuario usuario);
    List<Favorito> findByUsuarioIdUs(Long idUs);
    List<Favorito> findByUsuarioIdUsOrderByAgregadoEnDesc(Long idUs);
    Optional<Favorito> findByUsuarioIdUsAndProductoId(Long usuarioId, Long productoId);
    boolean existsByUsuarioIdUsAndProductoId(Long usuarioId, Long productoId);
    @Transactional
    @Modifying
    int deleteByUsuarioIdUsAndProductoId(Long usuarioId, Long productoId);
}
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioIdUs(Long idUs);
}
>>>>>>> origin/yesicag
