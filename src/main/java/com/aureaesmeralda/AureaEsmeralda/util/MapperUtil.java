package com.aureaesmeralda.AureaEsmeralda.util;

import com.aureaesmeralda.AureaEsmeralda.DTO.CertificadoDTO;
import com.aureaesmeralda.AureaEsmeralda.DTO.ProductoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Certificado;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;

public class MapperUtil {

    // Convierte de Entidad Producto a ProductoDTO
    public static ProductoDTO toProductoDTO(Producto producto) {
        if (producto == null)
            return null;

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

        // Navegación bidireccional segura: extraemos la propiedad sin causar bucle
        if (producto.getCertificado() != null) {
            dto.setCodigoCertificado(producto.getCertificado().getCodigoCert());
        }

        return dto;
    }

    // Convierte de Entidad Certificado a CertificadoDTO
    public static CertificadoDTO toCertificadoDTO(Certificado certificado) {
        if (certificado == null)
            return null;

        CertificadoDTO dto = new CertificadoDTO();
        dto.setIdCert(certificado.getIdCert());
        dto.setNombreGemologoCert(certificado.getNombreGemologoCert());
        dto.setCreadoEnCert(certificado.getCreadoEnCert());
        dto.setDetallesCert(certificado.getDetallesCert());
        dto.setCodigoCert(certificado.getCodigoCert());
        dto.setArchivoUrlCert(certificado.getArchivoUrlCert());
        dto.setImagenJoyaCert(certificado.getImagenJoyaCert());

        // Navegación bidireccional segura hacia el Producto padre
        if (certificado.getProducto() != null) {
            dto.setProductoId(certificado.getProducto().getIdPd());
            dto.setProductoNombre(certificado.getProducto().getNombrePd());
        }

        return dto;
    }
}
