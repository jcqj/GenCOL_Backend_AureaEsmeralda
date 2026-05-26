package com.aureaesmeralda.AureaEsmeralda.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prod")
    private Long id;

    @NotNull(message = "La categoría es obligatoria")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_prod", nullable = false)
    private Categoria categoria;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Column(name = "descripcion_prod", length = 500, nullable = false)
    private String descripcion;

    @NotNull(message = "El precio original es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(name = "precio_original_prod", nullable = false)
    private Double precioOriginal;

    @Min(value = 0, message = "El descuento no puede ser negativo")
    @Max(value = 100, message = "El descuento no puede superar el 100%")
    @Column(name = "descuento_prod")
    private Integer descuento = 0;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    @Column(name = "cantidad_prod", nullable = false)
    private Integer cantidad;

    @NotBlank(message = "La disponibilidad es obligatoria")
    @Column(name = "disponibilidad_prod", nullable = false)
    private String disponibilidad; // Ej. "Disponible", "Agotado", "Bajo pedido"

    @NotNull(message = "El estado best seller es obligatorio")
    @Column(name = "best_seller_prod", nullable = false)
    private Boolean bestSeller = false;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Certificado certificado;

    @Column(name = "creado_en_prod", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @PrePersist
    protected void onCreate() {
        this.creadoEn = LocalDateTime.now();
    }

    // Constructor vacío
    public Producto() {}

    // Constructor con campos
    public Producto(Categoria categoria, String descripcion, Double precioOriginal, Integer descuento, Integer cantidad, String disponibilidad, Boolean bestSeller) {
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.precioOriginal = precioOriginal;
        this.descuento = descuento;
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.bestSeller = bestSeller;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(Double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Boolean bestSeller) {
        this.bestSeller = bestSeller;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
        if (certificado != null && certificado.getProducto() != this) {
            certificado.setProducto(this);
        }
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }
}
