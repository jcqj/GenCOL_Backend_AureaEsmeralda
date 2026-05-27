package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.ProductoDTO;
import com.aureaesmeralda.AureaEsmeralda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite llamadas desde tu frontend local de forma temporal
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET: http://localhost:8080/api/productos
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarTodos() {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    // GET por ID: http://localhost:8080/api/productos/1
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    // GET por categoría: http://localhost:8080/api/productos/categoria/ANILLOS
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> listarPorCategoria(@PathVariable String categoria) {
        List<ProductoDTO> productos = productoService.obtenerProductosPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    // GET best sellers: http://localhost:8080/api/productos/bestsellers
    @GetMapping("/bestsellers")
    public ResponseEntity<List<ProductoDTO>> listarMasVendidos() {
        List<ProductoDTO> productos = productoService.obtenerMasVendidos();
        return ResponseEntity.ok(productos);
    }
}
