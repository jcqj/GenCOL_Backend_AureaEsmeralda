package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {
    Optional<Certificado> findByCodigo(String codigo);
    Optional<Certificado> findByCodigoIgnoreCase(String codigo);
    boolean existsByCodigoIgnoreCase(String codigo);
    Optional<Certificado> findByProductoId(Long productoId);
}
