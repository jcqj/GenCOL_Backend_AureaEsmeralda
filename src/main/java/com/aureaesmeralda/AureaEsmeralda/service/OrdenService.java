package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.DetalleOrdenRequestDTO;
import com.aureaesmeralda.AureaEsmeralda.DTO.OrdenDTO;
import com.aureaesmeralda.AureaEsmeralda.DTO.OrdenRequestDTO;
import com.aureaesmeralda.AureaEsmeralda.model.DetalleOrden;
import com.aureaesmeralda.AureaEsmeralda.model.Orden;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.OrdenRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public OrdenService(OrdenRepository ordenRepository,
                        UsuarioRepository usuarioRepository,
                        ProductoRepository productoRepository) {
        this.ordenRepository = ordenRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<OrdenDTO> listarTodas() {
        return ordenRepository.findAll().stream()
                .map(MapperUtil::toOrdenDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrdenDTO obtenerPorId(Long id) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
        return MapperUtil.toOrdenDTO(orden);
    }

    @Transactional(readOnly = true)
    public List<OrdenDTO> listarPorUsuario(Long usuarioId) {
        return ordenRepository.findByUsuarioIdUs(usuarioId).stream()
                .map(MapperUtil::toOrdenDTO)
                .toList();
    }

    @Transactional
    public OrdenDTO crearOrden(OrdenRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new RuntimeException("La orden debe tener al menos un detalle");
        }

        Orden orden = new Orden();
        orden.setUsuario(usuario);
        orden.setDireccionEnvioOrd(request.getDireccionEnvioOrd());
        orden.setEstadoOrd("PAGADO");

        BigDecimal total = BigDecimal.ZERO;

        for (DetalleOrdenRequestDTO detReq : request.getDetalles()) {
            Producto producto = productoRepository.findById(detReq.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + detReq.getProductoId()));

            if (!Boolean.TRUE.equals(producto.getDisponiblePd())) {
                throw new RuntimeException("El producto no esta disponible: " + producto.getNombrePd());
            }

            if (producto.getStockPd() < detReq.getCantidadDet()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombrePd()
                        + " (disponible: " + producto.getStockPd() + ", solicitado: " + detReq.getCantidadDet() + ")");
            }

            BigDecimal precioUnitario = MapperUtil.calcularPrecioConDescuento(
                    producto.getPrecioOriginalPd(), producto.getDescuentoPd());

            DetalleOrden detalle = new DetalleOrden();
            detalle.setOrden(orden);
            detalle.setProducto(producto);
            detalle.setCantidadDet(detReq.getCantidadDet());
            detalle.setPrecioUnitarioDet(precioUnitario);

            orden.getDetalles().add(detalle);

            producto.setStockPd(producto.getStockPd() - detReq.getCantidadDet());
            productoRepository.save(producto);

            total = total.add(precioUnitario.multiply(BigDecimal.valueOf(detReq.getCantidadDet())));
        }

        orden.setTotalOrd(total);
        Orden ordenGuardada = ordenRepository.save(orden);
        return MapperUtil.toOrdenDTO(ordenGuardada);
    }

    @Transactional
    public OrdenDTO actualizarEstado(Long id, String nuevoEstado) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + id));
        orden.setEstadoOrd(nuevoEstado.toUpperCase());
        Orden ordenActualizada = ordenRepository.save(orden);
        return MapperUtil.toOrdenDTO(ordenActualizada);
    }
}
