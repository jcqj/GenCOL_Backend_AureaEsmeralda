package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrdenDTO {
    private Long idOrd;
    private Long usuarioId;
    private BigDecimal totalOrd;
    private LocalDateTime creadoEnOrd;
    private String estadoOrd;
    private String direccionEnvioOrd;
    private List<DetalleOrdenDTO> detalles;

    public OrdenDTO() {}

    // Getters y Setters
    public Long getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(Long idOrd) {
        this.idOrd = idOrd;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public BigDecimal getTotalOrd() {
        return totalOrd;
    }

    public void setTotalOrd(BigDecimal totalOrd) {
        this.totalOrd = totalOrd;
    }

    public LocalDateTime getCreadoEnOrd() {
        return creadoEnOrd;
    }

    public void setCreadoEnOrd(LocalDateTime creadoEnOrd) {
        this.creadoEnOrd = creadoEnOrd;
    }

    public String getEstadoOrd() {
        return estadoOrd;
    }

    public void setEstadoOrd(String estadoOrd) {
        this.estadoOrd = estadoOrd;
    }

    public String getDireccionEnvioOrd() {
        return direccionEnvioOrd;
    }

    public void setDireccionEnvioOrd(String direccionEnvioOrd) {
        this.direccionEnvioOrd = direccionEnvioOrd;
    }

    public List<DetalleOrdenDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenDTO> detalles) {
        this.detalles = detalles;
    }
}
