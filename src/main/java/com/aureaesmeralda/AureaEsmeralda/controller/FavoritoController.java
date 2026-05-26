package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.model.Favorito;
import com.aureaesmeralda.AureaEsmeralda.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Favorito>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritoService.obtenerFavoritosPorUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/ordenados")
    public ResponseEntity<List<Favorito>> listarPorUsuarioOrdenados(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritoService.obtenerFavoritosPorUsuarioOrdenados(usuarioId));
    }

    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> verificarFavorito(
            @RequestParam Long usuarioId, @RequestParam Long productoId) {
        boolean esFav = favoritoService.esFavorito(usuarioId, productoId);
        return ResponseEntity.ok(Map.of("esFavorito", esFav));
    }

    @PostMapping
    public ResponseEntity<?> agregar(@RequestBody Map<String, Long> body) {
        Long usuarioId = body.get("usuarioId");
        Long productoId = body.get("productoId");
        if (usuarioId == null || productoId == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "usuarioId y productoId son obligatorios"));
        }
        try {
            Favorito favorito = favoritoService.agregarFavorito(usuarioId, productoId);
            return ResponseEntity.status(HttpStatus.CREATED).body(favorito);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            favoritoService.eliminarFavorito(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/usuario/{usuarioId}/producto/{productoId}")
    public ResponseEntity<?> eliminarPorUsuarioYProducto(
            @PathVariable Long usuarioId, @PathVariable Long productoId) {
        try {
            favoritoService.eliminarFavoritoPorUsuarioYProducto(usuarioId, productoId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
