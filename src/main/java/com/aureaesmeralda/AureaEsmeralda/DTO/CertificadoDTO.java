package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.time.LocalDate;

public class CertificadoDTO {
    private Long idCert;
    private String nombreGemologoCert;
    private LocalDate creadoEnCert;
    private String detallesCert;
    private String codigoCert;
    private String archivoUrlCert;
    private String imagenJoyaCert;
    private Long productoId;      // Solo la ID de referencia
    private String productoNombre; // Dato útil para la vista

    public CertificadoDTO() {}

    // Getters y Setters
    public Long getIdCert() { return idCert; }
    public void setIdCert(Long idCert) { this.idCert = idCert; }

    public String getNombreGemologoCert() { return nombreGemologoCert; }
    public void setNombreGemologoCert(String nombreGemologoCert) { this.nombreGemologoCert = nombreGemologoCert; }

    public LocalDate getCreadoEnCert() { return creadoEnCert; }
    public void setCreadoEnCert(LocalDate creadoEnCert) { this.creadoEnCert = creadoEnCert; }

    public String getDetallesCert() { return detallesCert; }
    public void setDetallesCert(String detallesCert) { this.detallesCert = detallesCert; }

    public String getCodigoCert() { return codigoCert; }
    public void setCodigoCert(String codigoCert) { this.codigoCert = codigoCert; }

    public String getArchivoUrlCert() { return archivoUrlCert; }
    public void setArchivoUrlCert(String archivoUrlCert) { this.archivoUrlCert = archivoUrlCert; }

    public String getImagenJoyaCert() { return imagenJoyaCert; }
    public void setImagenJoyaCert(String imagenJoyaCert) { this.imagenJoyaCert = imagenJoyaCert; }

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
}
