package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.CarritoDTO;
import com.aureaesmeralda.AureaEsmeralda.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // GET: http://localhost:8080/api/carritos/usuario/1
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long usuarioId) {
        CarritoDTO carrito = carritoService.obtenerCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(carrito);
    }

    // POST: http://localhost:8080/api/carritos/usuario/1/agregar?productoId=2&cantidad=1
    @PostMapping("/usuario/{usuarioId}/agregar")
    public ResponseEntity<CarritoDTO> agregarProducto(
            @PathVariable Long usuarioId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {

        CarritoDTO carrito = carritoService.agregarProductoAlCarrito(usuarioId, productoId, cantidad);
        return ResponseEntity.ok(carrito);
    }

    // DELETE: http://localhost:8080/api/carritos/usuario/1/eliminar/2
    @DeleteMapping("/usuario/{usuarioId}/eliminar/{productoId}")
    public ResponseEntity<CarritoDTO> eliminarProducto(
            @PathVariable Long usuarioId,
            @PathVariable Long productoId) {

        CarritoDTO carrito = carritoService.eliminarProductoDelCarrito(usuarioId, productoId);
        return ResponseEntity.ok(carrito);
    }
}
