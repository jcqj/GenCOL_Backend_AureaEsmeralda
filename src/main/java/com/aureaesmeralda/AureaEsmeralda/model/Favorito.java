package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "favoritos",
        uniqueConstraints =
            {
                @UniqueConstraint
                (
                    name = "uk_favorito_usuario_producto",
                    columnNames = { "us_id", "pd_id"}
                )
            }
        )
//@Table(name = "favoritos")
public class Favorito {
    //! Llave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fav")
    private Long idFav;

    //! CALVE FORANEA, Many To One con USUARIOS.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", nullable = false)
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    //! CLAVE FORANEA: Many To One con Productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pd_id", nullable = false)
    @NotNull(message = "El producto es obligatorio")
    private Producto producto; // Almacena el objeto Producto completo

    //! Fecha en la que el usuario guardó el producto en favoritos
    @Column(name = "creado_en_fav", nullable = false, updatable = false)
    private LocalDateTime creadoEnFav;

    //! Constructor vacío obligatorio para JPA
    public Favorito() {
    }

    //! Hook automático para asignar la fecha justo antes de insertar el registro
    @PrePersist
    protected void onCreate() {
        this.creadoEnFav = LocalDateTime.now();
    }

    //! ─────────────── GETTERS Y SETTERS ───────────────


    public Long getIdFav() {
        return idFav;
    }

    public void setIdFav(Long idFav) {
        this.idFav = idFav;
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

    public LocalDateTime getCreadoEnFav() {
        return creadoEnFav;
    }

    public void setCreadoEnFav(LocalDateTime creadoEnFav) {
        this.creadoEnFav = creadoEnFav;
    }
}
