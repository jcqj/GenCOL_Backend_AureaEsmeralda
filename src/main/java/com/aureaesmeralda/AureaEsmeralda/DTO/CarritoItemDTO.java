package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.math.BigDecimal;

public class CarritoItemDTO {
    private Long idIt;
    private Long productoId;
    private String productoNombre;
    private BigDecimal precioMomentaneoIt; // Precio entero
    private Integer cantidadIt;

    //! Getters y Setters
    public Long getIdIt() {
        return idIt;
    }

    public void setIdIt(Long idIt) {
        this.idIt = idIt;
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

    public BigDecimal getPrecioMomentaneoIt() {
        return precioMomentaneoIt;
    }

    public void setPrecioMomentaneoIt(BigDecimal precioMomentaneoIt) {
        this.precioMomentaneoIt = precioMomentaneoIt;
    }

    public Integer getCantidadIt() {
        return cantidadIt;
    }

    public void setCantidadIt(Integer cantidadIt) {
        this.cantidadIt = cantidadIt;
    }
}