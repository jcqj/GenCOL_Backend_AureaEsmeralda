package com.aureaesmeralda.AureaEsmeralda.util;

import com.aureaesmeralda.AureaEsmeralda.DTO.*;
import com.aureaesmeralda.AureaEsmeralda.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class MapperUtil {

    public static ProductoDTO toProductoDTO(Producto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setIdPd(producto.getIdPd());
        dto.setNombrePd(producto.getNombrePd());
        dto.setPrecioOriginalPd(producto.getPrecioOriginalPd());
        dto.setDescuentoPd(producto.getDescuentoPd());
        dto.setStockPd(producto.getStockPd());
        dto.setDisponiblePd(producto.getDisponiblePd());
        dto.setBestSellerPd(producto.getBestSellerPd());
        dto.setImagenPrincipalPd(producto.getImagenPrincipalPd());
        dto.setImagenSecundariaPd(producto.getImagenSecundariaPd());
        dto.setDescripcionPd(producto.getDescripcionPd());
        dto.setCategoriaPd(producto.getCategoriaPd());

        if (producto.getCertificado() != null) {
            dto.setCodigoCertificado(producto.getCertificado().getCodigoCert());
        }

        return dto;
    }

    public static CertificadoDTO toCertificadoDTO(Certificado certificado) {
        if (certificado == null) return null;

        CertificadoDTO dto = new CertificadoDTO();
        dto.setIdCert(certificado.getIdCert());
        dto.setNombreGemologoCert(certificado.getNombreGemologoCert());
        dto.setCreadoEnCert(certificado.getCreadoEnCert());
        dto.setDetallesCert(certificado.getDetallesCert());
        dto.setCodigoCert(certificado.getCodigoCert());
        dto.setArchivoUrlCert(certificado.getArchivoUrlCert());
        dto.setImagenJoyaCert(certificado.getImagenJoyaCert());

        if (certificado.getProducto() != null) {
            dto.setProductoId(certificado.getProducto().getIdPd());
            dto.setProductoNombre(certificado.getProducto().getNombrePd());
        }

        return dto;
    }

    public static CarritoDTO toCarritoDTO(Carrito carrito) {
        if (carrito == null) return null;

        CarritoDTO dto = new CarritoDTO();
        dto.setIdCar(carrito.getIdCar());
        dto.setUsuarioId(carrito.getUsuario().getIdUs());

        double total = 0.0;
        List<CarritoItemDTO> itemDTOs = new java.util.ArrayList<>();

        for (CarritoItem item : carrito.getItems()) {
            CarritoItemDTO itemDto = new CarritoItemDTO();
            itemDto.setIdIt(item.getIdIt());
            itemDto.setProductoId(item.getProducto().getIdPd());
            itemDto.setProductoNombre(item.getProducto().getNombrePd());
            itemDto.setPrecioMomentaneoIt(item.getPrecioMomentaneoIt());
            itemDto.setCantidadIt(item.getCantidadIt());
            itemDTOs.add(itemDto);

            total += item.getPrecioMomentaneoIt().doubleValue() * item.getCantidadIt();
        }

        dto.setItems(itemDTOs);
        dto.setTotalCarrito(total);
        return dto;
    }

    //! NUEVO: Mapper para favoritos (Evita ciclos infinitos)
    public static FavoritoDTO toFavoritoDTO(Favorito favorito) {
        if (favorito == null) return null;

        FavoritoDTO dto = new FavoritoDTO();
        dto.setIdFav(favorito.getIdFav());

        if (favorito.getProducto() != null) {
            Producto prod = favorito.getProducto();
            dto.setProductoId(prod.getIdPd());
            dto.setProductoNombre(prod.getNombrePd());
            dto.setProductoImagen(prod.getImagenPrincipalPd());

            // Calculamos el precio de vitrina con descuento entero
            BigDecimal precioConDescuento = calcularPrecioConDescuento(prod.getPrecioOriginalPd(), prod.getDescuentoPd());
            dto.setProductoPrecio(precioConDescuento);
        }

        return dto;
    }

    //! Utilidad para calcular el precio final entero exigido por el negocio
    public static BigDecimal calcularPrecioConDescuento(BigDecimal precioOriginal, Integer porcentajeDescuento) {
        if (porcentajeDescuento == null || porcentajeDescuento == 0) {
            return precioOriginal.setScale(0, RoundingMode.DOWN);
        }
        BigDecimal descuento = precioOriginal.multiply(new BigDecimal(porcentajeDescuento)).divide(new BigDecimal(100));
        return precioOriginal.subtract(descuento).setScale(0, RoundingMode.DOWN);
    }

    public static UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUs(usuario.getIdUs());
        dto.setNombreUs(usuario.getNombreUs());
        dto.setTelefonoUs(usuario.getTelefonoUs());
        dto.setCorreoUs(usuario.getCorreoUs());
        dto.setEstadoActivoUs(usuario.getEstadoActivoUs());
        dto.setRolUs(usuario.getRolUs());
        dto.setCreadoEnUs(usuario.getCreadoEnUs());
        return dto;
    }
}