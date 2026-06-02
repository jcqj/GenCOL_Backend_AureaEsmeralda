package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ord")
    private Long idOrd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", nullable = false)
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrden> detalles = new ArrayList<>();

    @Column(name = "total_ord", nullable = false, precision = 12, scale = 0)
    private BigDecimal totalOrd;

    @Column(name = "creado_en_ord", nullable = false, updatable = false)
    private LocalDateTime creadoEnOrd;

    @Column(name = "estado_ord", nullable = false, length = 30)
    private String estadoOrd = "PAGADO"; // Valor por defecto para pruebas

    @Column(name = "direccion_envio_ord", length = 255)
    private String direccionEnvioOrd;

    public Orden() {}

    @PrePersist
    protected void onCreate() {
        this.creadoEnOrd = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getIdOrd() {
        return idOrd;
    }

    public void setIdOrd(Long idOrd) {
        this.idOrd = idOrd;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrden> detalles) {
        this.detalles = detalles;
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
}
