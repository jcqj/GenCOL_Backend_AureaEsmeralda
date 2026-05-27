package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "productos")
public class Producto {

    public enum Categoria { ANILLOS, COLLARES, PULSERAS, ARETES }

    //! Llave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pd")
    private Long idPd;

    //! Nombre de Producto, obligatorio.
    @NotBlank(message = "Ingrese un nombre valido")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    @Column(name = "nombre_pd", nullable = false, length = 150)
    private String nombrePd;

    //! Precio Original: No puede ser nulo y debe ser un número positivo (mayor a 0).
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un número mayor a cero")
    @Column(name = "precio_original_pd", nullable = false, precision = 12, scale = 2) //para manejar 2 decimales
    private BigDecimal precioOriginalPd;

    //! Descuento: Obligatorio, pero puede ser 0 (si no tiene descuento). Máximo 100%
    @NotNull(message = "El descuento es obligatorio (coloque 0 si no aplica)")
    @Min(value = 0, message = "El descuento no puede ser negativo")
    @Max(value = 100, message = "El descuento no puede superar el 100%")
    @Column(name = "descuento_pd", nullable = false)
    private Integer descuentoPd = 0; // Inicializa en cero por defecto

    //! Stock - Inventario: No puede ser negativo
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock disponible no puede ser menor a cero")
    @Column(name = "stock_pd", nullable = false)
    private Integer stockPd;

    //! Disponible: Boolean para saber si se muestra en tienda o no
    @NotNull(message = "Debe especificar si el producto está disponible")
    @Column(name = "disponible_pd", nullable = false)
    private Boolean disponiblePd = true;

    //! Más vendido (Best Seller): Flag para destacar el producto
    @NotNull(message = "Debe especificar si es un producto destacado")
    @Column(name = "best_seller_pd", nullable = false)
    private Boolean bestSellerPd = false;

    //! URL de la Imagen Principal: Obligatoria para la vista de la tienda
    @NotBlank(message = "La URL de la imagen principal es obligatoria")
    @Size(max = 255, message = "La URL de la imagen es demasiado larga (máximo 255 caracteres)")
    @Column(name = "imagen_principal_pd", nullable = false, length = 255)
    private String imagenPrincipalPd;

    //! URL de la Imagen Secundaria: Opcional
    @Size(max = 255, message = "La URL de la imagen secundaria no puede superar los 255 caracteres")
    @Column(name = "imagen_secundaria_pd", length = 255)
    private String imagenSecundariaPd;

    //! Descripción: Campo de texto largo (mapeado como TEXT en BD)
    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(name = "descripcion_pd", nullable = false, columnDefinition = "TEXT")
    private String descripcionPd;

    //! Fecha en la que se subió el producto
    @Column(name = "creado_en_pd", nullable = false, updatable = false)
    private LocalDateTime creadoEnPd;

    //! Categoria de producto
//    @NotBlank(message = "La categoría es obligatoria")
//    @Pattern(
//            regexp = "^(ANILLOS|COLLARES|PULSERAS)$",
//            message = "Categoría inválida. Los valores permitidos son: ANILLOS, COLLARES o PULSERAS"
//    )
//    @Column(name = "categoria_pd", nullable = false, length = 30)
//    private String categoriaPd;

    @NotNull(message = "La categoría es obligatoria")
    @Enumerated(EnumType.STRING) // se usa el enum de arriba
    @Column(name = "categoria_pd", nullable = false, length = 30)
    private Categoria categoriaPd;

    // ! ────────── RELACIONES BIDIRECCIONALES ──────────

    //! Un producto tiene un único certificado (1:1 inverso)
    //? cascade = CascadeType.ALL permite que si borras el producto, se borre su certificado automáticamente.
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Certificado certificado;

    //! Un producto puede estar presente en muchos ítems de carritos (1:N inverso)
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<CarritoItem> carritoItems = new ArrayList<>();

    //! Un producto puede estar en las listas de favoritos de muchos usuarios (1:N inverso)
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private List<Favorito> favoritos = new ArrayList<>();

    //! Constructor vacio, obligatorio para JPA.
    public Producto() {}

    //! Callback para asignar la fecha de creación automáticamente antes de guardar
    @PrePersist
    protected void onCreate() {
        this.creadoEnPd = LocalDateTime.now();
    }

    //! ─────────────── GETTERS Y SETTERS ───────────────

    public Long getIdPd() {
        return idPd;
    }

    public String getNombrePd() {
        return nombrePd;
    }

    public void setNombrePd(String nombrePd) {
        this.nombrePd = nombrePd;
    }

    public BigDecimal getPrecioOriginalPd() {
        return precioOriginalPd;
    }

    public void setPrecioOriginalPd(BigDecimal precioOriginalPd) {
        this.precioOriginalPd = precioOriginalPd;
    }

    public Integer getDescuentoPd() {
        return descuentoPd;
    }

    public void setDescuentoPd(Integer descuentoPd) {
        this.descuentoPd = descuentoPd;
    }

    public Integer getStockPd() {
        return stockPd;
    }

    public void setStockPd(Integer stockPd) {
        this.stockPd = stockPd;
    }

    public Boolean getDisponiblePd() {
        return disponiblePd;
    }

    public void setDisponiblePd(Boolean disponiblePd) {
        this.disponiblePd = disponiblePd;
    }

    public Boolean getBestSellerPd() {
        return bestSellerPd;
    }

    public void setBestSellerPd(Boolean bestSellerPd) {
        this.bestSellerPd = bestSellerPd;
    }

    public String getImagenPrincipalPd() {
        return imagenPrincipalPd;
    }

    public void setImagenPrincipalPd(String imagenPrincipalPd) {
        this.imagenPrincipalPd = imagenPrincipalPd;
    }

    public String getImagenSecundariaPd() {
        return imagenSecundariaPd;
    }

    public void setImagenSecundariaPd(String imagenSecundariaPd) {
        this.imagenSecundariaPd = imagenSecundariaPd;
    }

    public String getDescripcionPd() {
        return descripcionPd;
    }

    public void setDescripcionPd(String descripcionPd) {
        this.descripcionPd = descripcionPd;
    }

    public LocalDateTime getCreadoEnPd() {
        return creadoEnPd;
    }

    public void setCreadoEnPd(LocalDateTime creadoEnPd) {
        this.creadoEnPd = creadoEnPd;
    }

    public Categoria getCategoriaPd() {
        return categoriaPd;
    }

    public void setCategoriaPd(Categoria categoriaPd) {
        this.categoriaPd = categoriaPd;
    }

    public void setIdPd(Long idPd) {
        this.idPd = idPd;
    }

    public Certificado getCertificado() {
        return certificado;
    }

    public void setCertificado(Certificado certificado) {
        this.certificado = certificado;
    }

    public List<CarritoItem> getCarritoItems() {
        return carritoItems;
    }

    public void setCarritoItems(List<CarritoItem> carritoItems) {
        this.carritoItems = carritoItems;
    }

    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }
}
