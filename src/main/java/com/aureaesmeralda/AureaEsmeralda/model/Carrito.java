package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito
{
    //! Llave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Long idCar;

    //! CLAVE FORANEA: Relacion con el Usuario
    //? Usamos @OneToOne si el usuario solo puede tener un carrito activo en toda la historia.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "us_id", nullable = false, unique = true)
    @NotNull(message = "El usuario es obligatorio")
    private Usuario usuario;

    //! RELACION BIDIRECCIONAL: Lista de ítems dentro de este carrito
    //? cascade = CascadeType.ALL, significa que si borras el carrito, se borran automaticamente sus ítems.
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoItem> items = new ArrayList<>();

    //! Fecha de creacion
    @Column(name = "creado_en_car", nullable = false, updatable = false)
    private LocalDateTime creadoEnCar;

    //! Fecha de ultima actualizacion (cuando agrega o quita productos)
    @Column(name = "actualizado_en_car", nullable = false)
    private LocalDateTime actualizadoEnCar;

    //! Constructor vacío obligatorio
    public Carrito() {}

    //! Hooks automaticos para las fechas del ciclo de vida
    @PrePersist
    protected void onCreate() {
        this.creadoEnCar = LocalDateTime.now();
        this.actualizadoEnCar = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.actualizadoEnCar = LocalDateTime.now();
    }

    //! ─────────────── GETTERS Y SETTERS ───────────────

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<CarritoItem> getItems() {
        return items;
    }

    public void setItems(List<CarritoItem> items) {
        this.items = items;
    }

    public LocalDateTime getCreadoEnCar() {
        return creadoEnCar;
    }

    public void setCreadoEnCar(LocalDateTime creadoEnCar) {
        this.creadoEnCar = creadoEnCar;
    }

    public LocalDateTime getActualizadoEnCar() {
        return actualizadoEnCar;
    }

    public void setActualizadoEnCar(LocalDateTime actualizadoEnCar) {
        this.actualizadoEnCar = actualizadoEnCar;
    }
}