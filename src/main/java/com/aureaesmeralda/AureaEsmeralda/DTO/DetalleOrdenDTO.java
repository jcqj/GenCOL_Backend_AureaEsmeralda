package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.math.BigDecimal;

public class DetalleOrdenDTO {
    private Long idDet;
    private Long productoId;
    private String productoNombre;
    private Integer cantidadDet;
    private BigDecimal precioUnitarioDet;

    public DetalleOrdenDTO() {}

    // Getters y Setters
    public Long getIdDet() {
        return idDet;
    }

    public void setIdDet(Long idDet) {
        this.idDet = idDet;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidadDet() {
        return cantidadDet;
    }

    public void setCantidadDet(Integer cantidadDet) {
        this.cantidadDet = cantidadDet;
    }

    public BigDecimal getPrecioUnitarioDet() {
        return precioUnitarioDet;
    }

    public void setPrecioUnitarioDet(BigDecimal precioUnitarioDet) {
        this.precioUnitarioDet = precioUnitarioDet;
    }
}
