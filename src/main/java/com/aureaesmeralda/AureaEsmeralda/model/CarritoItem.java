package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(
        name = "carrito_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_carrito_producto",
                        columnNames = {"car_id", "pd_id"} // Evita que el mismo producto se inserte en dos filas diferentes del mismo carrito
                )
        }
)
public class CarritoItem {

    //! Llave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_it")
    private Long idIt;

    //! CLAVE FORÁNEA: Conexión con el carrito contenedor (Padre)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    @NotNull(message = "El carrito es obligatorio")
    private Carrito carrito;

    //! CLAVE FORÁNEA: Conexión con el producto seleccionado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pd_id", nullable = false)
    @NotNull(message = "El producto es obligatorio")
    private Producto producto;

    //! PRECIO MOMENTÁNEO: Almacena el valor del producto al agregarlo (Estricto Entero)
    @NotNull(message = "El precio momentáneo es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    @Column(name = "precio_momentaneo_it", nullable = false, precision = 12, scale = 0)
    private BigDecimal precioMomentaneoIt;

    //! CANTIDAD: Número de unidades que el usuario desea comprar del producto
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima a añadir debe ser 1")
    @Column(name = "cantidad_it", nullable = false)
    private Integer cantidadIt;

    //! Constructor vacío obligatorio
    public CarritoItem() {
    }

    //! ─────────────── GETTERS Y SETTERS ───────────────

    public Long getIdIt() {
        return idIt;
    }

    public void setIdIt(Long idIt) {
        this.idIt = idIt;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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