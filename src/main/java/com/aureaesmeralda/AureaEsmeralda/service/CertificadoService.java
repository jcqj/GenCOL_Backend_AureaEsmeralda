package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.CertificadoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Certificado;
import com.aureaesmeralda.AureaEsmeralda.repository.CertificadoRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public CertificadoDTO obtenerCertificadoPorId(Long id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado con ID " + id + " no encontrado."));
        return MapperUtil.toCertificadoDTO(certificado);
    }

    public CertificadoDTO obtenerCertificadoPorCodigo(String codigo) {
        Certificado certificado = certificadoRepository.findByCodigoCert(codigo)
                .orElseThrow(() -> new RuntimeException("Certificado con código " + codigo + " no encontrado."));
        return MapperUtil.toCertificadoDTO(certificado);
    }

    public List<CertificadoDTO> obtenerTodos() {
        return certificadoRepository.findAll().stream()
                .map(MapperUtil::toCertificadoDTO)
                .collect(Collectors.toList());
    }

    public CertificadoDTO actualizarCertificado(Long id, Certificado datos) {
        Certificado existente = certificadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificado con ID " + id + " no encontrado."));

        existente.setCodigoCert(datos.getCodigoCert());
        existente.setNombreGemologoCert(datos.getNombreGemologoCert());
        existente.setCreadoEnCert(datos.getCreadoEnCert());
        existente.setArchivoUrlCert(datos.getArchivoUrlCert());
        existente.setImagenJoyaCert(datos.getImagenJoyaCert());
        existente.setDetallesCert(datos.getDetallesCert());

        return MapperUtil.toCertificadoDTO(certificadoRepository.save(existente));
    }

    public void eliminarCertificado(Long id) {
        if (!certificadoRepository.existsById(id)) {
            throw new RuntimeException("Certificado con ID " + id + " no encontrado.");
        }
        certificadoRepository.deleteById(id);
    }
}
