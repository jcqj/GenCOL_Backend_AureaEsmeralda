package src.main.java.com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.repository.CertificadoRepository;
import org.springframework.stereotype.Service;
import com.aureaesmeralda.AureaEsmeralda.model.Certificado;

import java.util.List;
import java.util.Optional;

@Service
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public Certificado crearCertificado(Certificado certificado) {
        return certificadoRepository.save(certificado);
    }

    public List<Certificado> obtenerTodos() {
        return certificadoRepository.findAll();
    }

    public Optional<Certificado> obtenerPorId(Long id) {
        return certificadoRepository.findById(id);
    }

    public Optional<Certificado> obtenerPorCodigo(String codigo) {
        return certificadoRepository.findByCodigoIgnoreCase(codigo);
    }

    public Optional<Certificado> obtenerProductoId(Long productoId) {
        return certificadoRepository.findByProductoId(productoId);
    }

    public Certificado actualizarCertificado(Long id, Certificado datos) {
        Certificado existente = certificadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El certificado con ID " + id + " no existe."));

        existente.setCodigo(datos.getCodigo());
        existente.setNombreGemologo(datos.getNombreGemologo());
        existente.setFechaCert(datos.getFechaCert());
        existente.setUrlPdf(datos.getUrlPdf());
        existente.setImagenJoya(datos.getImagenJoya());

        return certificadoRepository.save(existente);
    }

    public void eliminarCertificado(Long id) {
        if (!certificadoRepository.existsById(id)) {
            throw new IllegalArgumentException("El certificado con ID " + id + " no existe.");
        }
        certificadoRepository.deleteById(id);
    }
}
