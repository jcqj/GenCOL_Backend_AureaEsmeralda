package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.CertificadoDTO;
import com.aureaesmeralda.AureaEsmeralda.service.CertificadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/certificados")
@CrossOrigin(origins = "*")
public class CertificadoController {

    private final CertificadoService certificadoService;

    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    // GET por ID: http://localhost:8080/api/certificados/1
    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> buscarPorId(@PathVariable Long id) {
        CertificadoDTO certificado = certificadoService.obtenerCertificadoPorId(id);
        return ResponseEntity.ok(certificado);
    }

    // GET por código único: http://localhost:8080/api/certificados/codigo/CERT-001
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CertificadoDTO> buscarPorCodigo(@PathVariable String codigo) {
        CertificadoDTO certificado = certificadoService.obtenerCertificadoPorCodigo(codigo);
        return ResponseEntity.ok(certificado);
    }
}