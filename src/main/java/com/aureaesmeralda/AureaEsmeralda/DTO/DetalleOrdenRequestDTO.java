package com.aureaesmeralda.AureaEsmeralda.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DetalleOrdenRequestDTO {
    @NotNull(message = "El producto es obligatorio")
    private Long productoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer cantidadDet;

    public DetalleOrdenRequestDTO() {}

    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public Integer getCantidadDet() { return cantidadDet; }
    public void setCantidadDet(Integer cantidadDet) { this.cantidadDet = cantidadDet; }
}
