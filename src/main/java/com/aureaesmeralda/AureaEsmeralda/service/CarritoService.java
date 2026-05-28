package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.CarritoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Carrito;
import com.aureaesmeralda.AureaEsmeralda.model.CarritoItem;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.CarritoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Obtener el carrito (Capa Externa/Web)
    @Transactional(readOnly = true)
    public CarritoDTO obtenerCarritoPorUsuario(Long usuarioId) {
        Carrito carrito = buscarOAdjudicarCarritoEntidad(usuarioId);
        return MapperUtil.toCarritoDTO(carrito);
    }

    // 2. Agregar un producto al carrito (Optimizado)
    @Transactional
    public CarritoDTO agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad) {
        // Buscamos el producto
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));

        // Validamos el Stock Inicial
        if (producto.getStockPd() < cantidad) {
            throw new IllegalStateException("Stock insuficiente. Solo quedan " + producto.getStockPd() + " unidades.");
        }

        // REGLA DE ORO: Buscamos la entidad del carrito directamente sin consultar de más
        Carrito carrito = buscarOAdjudicarCarritoEntidad(usuarioId);

        // Verificamos si el producto ya está en el carrito
        Optional<CarritoItem> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getProducto().getIdPd().equals(productoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            CarritoItem item = itemExistente.get();
            int nuevaCantidad = item.getCantidadIt() + cantidad;

            if (producto.getStockPd() < nuevaCantidad) {
                throw new IllegalStateException("No puedes agregar más unidades. Supera el stock disponible.");
            }
            item.setCantidadIt(nuevaCantidad);
        } else {
            CarritoItem nuevoItem = new CarritoItem();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setPrecioMomentaneoIt(producto.getPrecioOriginalPd());
            nuevoItem.setCantidadIt(cantidad);

            carrito.getItems().add(nuevoItem);
        }

        Carrito carritoActualizado = carritoRepository.save(carrito);
        return MapperUtil.toCarritoDTO(carritoActualizado);
    }

    // 3. Eliminar un producto por completo del carrito
    @Transactional
    public CarritoDTO eliminarProductoDelCarrito(Long usuarioId, Long productoId) {
        Carrito carrito = carritoRepository.findByUsuarioIdUs(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado para el usuario con ID: " + usuarioId));

        carrito.getItems().removeIf(item -> item.getProducto().getIdPd().equals(productoId));

        Carrito carritoActualizado = carritoRepository.save(carrito);
        return MapperUtil.toCarritoDTO(carritoActualizado);
    }

    /**
     * MÉTODOS DE SOPORTE INTERNO (Auxiliar privado para reutilizar código y no repetir SQL)
     */
    private Carrito buscarOAdjudicarCarritoEntidad(Long usuarioId) {
        return carritoRepository.findByUsuarioIdUs(usuarioId)
                .orElseGet(() -> {
                    Usuario usuario = usuarioRepository.findById(usuarioId)
                            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + usuarioId));

                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    nuevoCarrito.setItems(new ArrayList<>()); // Red de seguridad: Evita el NullPointerException
                    return carritoRepository.save(nuevoCarrito);
                });
    }
}