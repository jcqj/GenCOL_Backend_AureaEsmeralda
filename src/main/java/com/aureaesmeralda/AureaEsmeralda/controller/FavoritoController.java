package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.FavoritoDTO;
import com.aureaesmeralda.AureaEsmeralda.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favoritos")
@CrossOrigin(origins = "*")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    // GET: http://localhost:8080/api/favoritos/usuario/1
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoDTO>> listarFavoritosPorUsuario(@PathVariable Long usuarioId) {
        List<FavoritoDTO> favoritos = favoritoService.obtenerFavoritosPorUsuario(usuarioId);
        return ResponseEntity.ok(favoritos);
    }

    // POST: http://localhost:8080/api/favoritos/agregar?usuarioId=1&productoId=2
    @PostMapping("/agregar")
    public ResponseEntity<FavoritoDTO> agregarAlosFavoritos(
            @RequestParam Long usuarioId,
            @RequestParam Long productoId) {
        FavoritoDTO nuevoFavorito = favoritoService.agregarFavorito(usuarioId, productoId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoFavorito);
    }

    // DELETE: http://localhost:8080/api/favoritos/eliminar/5
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> quitarDeFavoritos(@PathVariable Long id) {
        favoritoService.eliminarFavoritoPorIdDirecto(id);
        return ResponseEntity.noContent().build();
    }
}