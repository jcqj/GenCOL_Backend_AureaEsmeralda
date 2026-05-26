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

import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener el carrito de un usuario. Si no existe, se crea uno automáticamente.
    @Transactional
    public CarritoDTO obtenerCarritoPorUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Intentar buscar el carrito existente, sino inicializarlo
        Carrito carrito = carritoRepository.findByUsuarioIdUs(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setUsuario(usuario);
                    return carritoRepository.save(nuevoCarrito);
                });

        return MapperUtil.toCarritoDTO(carrito);
    }

    // Agregar un producto al carrito
    @Transactional
    public CarritoDTO agregarProductoAlCarrito(Long usuarioId, Long productoId, Integer cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 1. Validar Stock General de la Joyería
        if (producto.getStockPd() < cantidad) {
            throw new RuntimeException("Stock insuficiente. Solo quedan " + producto.getStockPd() + " unidades.");
        }

        // 2. Obtener el Carrito de la entidad
        CarritoDTO carritoDto = obtenerCarritoPorUsuario(usuarioId);
        Carrito carrito = carritoRepository.findById(carritoDto.getIdCar()).get();

        // 3. Verificar si el producto ya está en el carrito
        Optional<CarritoItem> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getProducto().getIdPd().equals(productoId))
                .findFirst();

        if (itemExistente.isPresent()) {
            CarritoItem item = itemExistente.get();
            int nuevaCantidad = item.getCantidadIt() + cantidad;

            // Re-validar stock sumando lo que ya tenía en el carro
            if (producto.getStockPd() < nuevaCantidad) {
                throw new RuntimeException("No puedes agregar más unidades. Supera el stock disponible.");
            }
            item.setCantidadIt(nuevaCantidad);
        } else {
            // Crear nuevo ítem en el carro
            CarritoItem nuevoItem = new CarritoItem();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            // El precio original ya viene formateado como entero (scale = 0) desde la base de datos
            nuevoItem.setPrecioMomentaneoIt(producto.getPrecioOriginalPd());
            nuevoItem.setCantidadIt(cantidad);

            // Relación bidireccional en memoria
            carrito.getItems().add(nuevoItem);
        }

        // Al guardar el padre (Carrito), gracias al CascadeType.ALL se guardan/actualizan los hijos (CarritoItem)
        Carrito carritoActualizado = carritoRepository.save(carrito);
        return MapperUtil.toCarritoDTO(carritoActualizado);
    }

    // Eliminar un producto por completo del carrito
    @Transactional
    public CarritoDTO eliminarProductoDelCarrito(Long usuarioId, Long productoId) {
        Carrito carrito = carritoRepository.findByUsuarioIdUs(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        // Se remueve de la lista del padre y orphanRemoval = true lo borra de la BD
        carrito.getItems().removeIf(item -> item.getProducto().getIdPd().equals(productoId));

        Carrito carritoActualizado = carritoRepository.save(carrito);
        return MapperUtil.toCarritoDTO(carritoActualizado);

    }
}