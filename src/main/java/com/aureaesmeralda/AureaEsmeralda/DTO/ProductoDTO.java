package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.math.BigDecimal;

public class ProductoDTO {
    private Long idPd;
    private String nombrePd;
    private BigDecimal precioOriginalPd;
    private Integer descuentoPd;
    private Integer stockPd;
    private Boolean disponiblePd;
    private Boolean bestSellerPd;
    private String imagenPrincipalPd;
    private String imagenSecundariaPd;
    private String descripcionPd;
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