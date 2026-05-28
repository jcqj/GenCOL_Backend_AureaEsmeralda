package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.CertificadoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Certificado;
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

    // GET certificados: http://localhost:8080/api/certificados
    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarTodos() {
        return ResponseEntity.ok(certificadoService.obtenerTodos());
    }

    // GET por ID: http://localhost:8080/api/certificados/1
    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.obtenerCertificadoPorId(id));
    }

    // GET por código único: http://localhost:8080/api/certificados/codigo/CERT-001
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<CertificadoDTO> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(certificadoService.obtenerCertificadoPorCodigo(codigo));
    }

    // PUT: http://localhost:8080/api/certificados/1
    @PutMapping("/{id}")
    public ResponseEntity<CertificadoDTO> actualizar(
            @PathVariable Long id,
            @RequestBody Certificado datos) {
        return ResponseEntity.ok(certificadoService.actualizarCertificado(id, datos));
    }

    // DELETE: http://localhost:8080/api/certificados/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        certificadoService.eliminarCertificado(id);
        return ResponseEntity.noContent().build();
    }
}