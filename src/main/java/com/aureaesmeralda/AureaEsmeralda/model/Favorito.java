package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "favoritos",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_favorito_usuario_producto",
                columnNames = {"usuario_id", "producto_id"}
        )
)
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fav")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(name = "agregado_en_fav", nullable = false, updatable = false)
    private LocalDateTime agregadoEn;

    @PrePersist
    protected void onCreate() {
        this.agregadoEn = LocalDateTime.now();
    }

    // Constructor vacío
    public Favorito() {}

    // Constructor con campos
    public Favorito(Usuario usuario, Producto producto) {
        this.usuario = usuario;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getAgregadoEn() {
        return agregadoEn;
    }

    public void setAgregadoEn(LocalDateTime agregadoEn) {
        this.agregadoEn = agregadoEn;
    }
}
