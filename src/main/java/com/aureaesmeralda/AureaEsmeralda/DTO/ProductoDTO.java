package com.aureaesmeralda.AureaEsmeralda.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ProductoDTO {
    private Long idPd;

    @NotBlank(message = "Ingrese un nombre valido")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres")
    private String nombrePd;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un numero mayor a cero")
    private BigDecimal precioOriginalPd;

    @NotNull(message = "El descuento es obligatorio")
    @Min(value = 0, message = "El descuento no puede ser negativo")
    @Max(value = 100, message = "El descuento no puede superar el 100%")
    private Integer descuentoPd;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock disponible no puede ser menor a cero")
    private Integer stockPd;

    @NotNull(message = "Debe especificar si el producto esta disponible")
    private Boolean disponiblePd;

    @NotNull(message = "Debe especificar si es un producto destacado")
    private Boolean bestSellerPd;

    @NotBlank(message = "La URL de la imagen principal es obligatoria")
    @Size(max = 255, message = "La URL de la imagen principal no puede superar los 255 caracteres")
    private String imagenPrincipalPd;

    @Size(max = 255, message = "La URL de la imagen secundaria no puede superar los 255 caracteres")
    private String imagenSecundariaPd;

    @NotBlank(message = "La descripcion del producto es obligatoria")
    @Size(max = 255, message = "La descripcion no puede superar los 255 caracteres")
    private String descripcionPd;

    @NotBlank(message = "La categoria es obligatoria")
    @Pattern(
            regexp = "^(ANILLOS|COLLARES|PULSERAS)$",
            message = "Categoria invalida. Los valores permitidos son: ANILLOS, COLLARES o PULSERAS"
    )
    private String categoriaPd;

    private String codigoCertificado;

    // Constructor vacío
    public ProductoDTO() {
    }

    // Getters y Setters
    public Long getIdPd() {
        return idPd;
    }

    public void setIdPd(Long idPd) {
        this.idPd = idPd;
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

    public String getCategoriaPd() {
        return categoriaPd;
    }

    public void setCategoriaPd(String categoriaPd) {
        this.categoriaPd = categoriaPd;
    }

    public String getCodigoCertificado() {
        return codigoCertificado;
    }

    public void setCodigoCertificado(String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }
}
