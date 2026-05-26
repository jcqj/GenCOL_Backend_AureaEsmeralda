package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.model.Categoria;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> obtenerBestSellers() {
        return productoRepository.findByBestSellerTrue();
    }

    public List<Producto> obtenerPorDisponibilidad(String disponibilidad) {
        return productoRepository.findByDisponibilidadIgnoreCase(disponibilidad);
    }

    public List<Producto> buscarPorDescripcion(String descripcion) {
        return productoRepository.findByDescripcionContainingIgnoreCase(descripcion);
    }

    public List<Producto> obtenerPorRangoPrecio(Double min, Double max) {
        return productoRepository.findByPrecioOriginalBetween(min, max);
    }

    public List<Producto> obtenerPorCategoriaYBestSeller(Categoria categoria) {
        return productoRepository.findByCategoriaAndBestSellerTrue(categoria);
    }

    public List<Producto> obtenerPorCategoriaYRangoPrecio(Categoria categoria, Double min, Double max) {
        return productoRepository.findByCategoriaAndPrecioOriginalBetween(categoria, min, max);
    }

    public List<Producto> obtenerPorDisponibilidadYRangoPrecio(String disponibilidad, Double min, Double max) {
        return productoRepository.findByDisponibilidadIgnoreCaseAndPrecioOriginalBetween(disponibilidad, min, max);
    }

    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto con el ID " + id + " no existe."));

        producto.setCategoria(productoDetalles.getCategoria());
        producto.setDescripcion(productoDetalles.getDescripcion());
        producto.setPrecioOriginal(productoDetalles.getPrecioOriginal());
        producto.setDescuento(productoDetalles.getDescuento());
        producto.setCantidad(productoDetalles.getCantidad());
        producto.setDisponibilidad(productoDetalles.getDisponibilidad());
        producto.setBestSeller(productoDetalles.getBestSeller());

        if (productoDetalles.getCertificado() != null) {
            producto.setCertificado(productoDetalles.getCertificado());
        }

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El producto con el ID " + id + " no existe."));
        productoRepository.delete(producto);
    }
}
