package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Certificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    //! Buscar un certificado específico mediante su código único
    Optional<Certificado> findByCodigoCert(String codigoCert);
}
