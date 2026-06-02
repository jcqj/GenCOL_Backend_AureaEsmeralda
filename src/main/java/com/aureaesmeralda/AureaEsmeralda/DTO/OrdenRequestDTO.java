package com.aureaesmeralda.AureaEsmeralda.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrdenRequestDTO {
    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;
    private String direccionEnvioOrd;

    @Valid
    @NotEmpty(message = "La orden debe tener al menos un detalle")
    private List<DetalleOrdenRequestDTO> detalles;

    public OrdenRequestDTO() {}

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public String getDireccionEnvioOrd() { return direccionEnvioOrd; }
    public void setDireccionEnvioOrd(String direccionEnvioOrd) { this.direccionEnvioOrd = direccionEnvioOrd; }
    public List<DetalleOrdenRequestDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrdenRequestDTO> detalles) { this.detalles = detalles; }
}
