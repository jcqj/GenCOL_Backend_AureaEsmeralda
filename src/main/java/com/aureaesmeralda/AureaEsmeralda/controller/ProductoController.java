package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.model.Categoria;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> listarPorCategoria(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(productoService.obtenerPorCategoria(categoria));
    }

    @GetMapping("/best-sellers")
    public ResponseEntity<List<Producto>> listarBestSellers() {
        return ResponseEntity.ok(productoService.obtenerBestSellers());
    }

    @GetMapping("/disponibilidad/{disponibilidad}")
    public ResponseEntity<List<Producto>> listarPorDisponibilidad(@PathVariable String disponibilidad) {
        return ResponseEntity.ok(productoService.obtenerPorDisponibilidad(disponibilidad));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorDescripcion(@RequestParam String q) {
        return ResponseEntity.ok(productoService.buscarPorDescripcion(q));
    }

    @GetMapping("/rango-precio")
    public ResponseEntity<List<Producto>> listarPorRangoPrecio(
            @RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productoService.obtenerPorRangoPrecio(min, max));
    }

    @GetMapping("/categoria/{categoria}/best-sellers")
    public ResponseEntity<List<Producto>> listarPorCategoriaYBestSeller(@PathVariable Categoria categoria) {
        return ResponseEntity.ok(productoService.obtenerPorCategoriaYBestSeller(categoria));
    }

    @GetMapping("/categoria/{categoria}/rango-precio")
    public ResponseEntity<List<Producto>> listarPorCategoriaYRangoPrecio(
            @PathVariable Categoria categoria,
            @RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productoService.obtenerPorCategoriaYRangoPrecio(categoria, min, max));
    }

    @GetMapping("/disponibilidad/{disponibilidad}/rango-precio")
    public ResponseEntity<List<Producto>> listarPorDisponibilidadYRangoPrecio(
            @PathVariable String disponibilidad,
            @RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(productoService.obtenerPorDisponibilidadYRangoPrecio(disponibilidad, min, max));
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto nuevo = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Producto actualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
