package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.math.BigDecimal;

public class FavoritoDTO {
    private Long idFav;
    private Long productoId;
    private String productoNombre;
    private BigDecimal productoPrecio; // Precio entero de vitrina
    private String productoImagen;

    public FavoritoDTO() {}

    // Getters y Setters
    public Long getIdFav() { return idFav; }
    public void setIdFav(Long idFav) { this.idFav = idFav; }
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public BigDecimal getProductoPrecio() { return productoPrecio; }
    public void setProductoPrecio(BigDecimal productoPrecio) { this.productoPrecio = productoPrecio; }
    public String getProductoImagen() { return productoImagen; }
    public void setProductoImagen(String productoImagen) { this.productoImagen = productoImagen; }
}