package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.ProductoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(MapperUtil::toProductoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return MapperUtil.toProductoDTO(producto);

    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosPorCategoria(String categoria) {
        return productoRepository.findByCategoriaPdIgnoreCase(categoria).stream()
                .map(MapperUtil::toProductoDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerMasVendidos() {
        return productoRepository.findByBestSellerPdTrue().stream()
                .map(MapperUtil::toProductoDTO)
                .collect(Collectors.toList());
    }
}