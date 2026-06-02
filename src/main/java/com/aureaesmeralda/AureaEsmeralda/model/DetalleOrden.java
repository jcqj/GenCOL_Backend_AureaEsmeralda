package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "detalles_orden")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_det")
    private Long idDet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orden_id", nullable = false)
    private Orden orden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    @NotNull(message = "El producto es obligatorio")
    private Producto producto;

    @Column(name = "cantidad_det", nullable = false)
    private Integer cantidadDet;

    @Column(name = "precio_unitario_det", nullable = false, precision = 12, scale = 0)
    private BigDecimal precioUnitarioDet;

    public DetalleOrden() {}

    // Getters y Setters
    public Long getIdDet() {
        return idDet;
    }

    public void setIdDet(Long idDet) {
        this.idDet = idDet;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
