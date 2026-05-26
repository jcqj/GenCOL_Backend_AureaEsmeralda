package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.util.List;

public class CarritoDTO {
    private Long idCar;
    private Long usuarioId;
    private List<CarritoItemDTO> items;
    private Double totalCarrito; // Calculado dinámicamente para el frontend

    //! Getters y Setters
    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<CarritoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CarritoItemDTO> items) {
        this.items = items;
    }

    public Double getTotalCarrito() {
        return totalCarrito;
    }

    public void setTotalCarrito(Double totalCarrito) {
        this.totalCarrito = totalCarrito;
    }
}
