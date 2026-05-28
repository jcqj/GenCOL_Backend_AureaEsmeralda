package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.UsuarioDTO;
import com.aureaesmeralda.AureaEsmeralda.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. GET: http://localhost:8080/api/usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // 2. GET: http://localhost:8080/api/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // 3. GET: http://localhost:8080/api/usuarios/buscar?correo=ejemplo@joyeria.com
    @GetMapping("/buscar")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorCorreo(@RequestParam String correo) {
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorCorreo(correo);
        return ResponseEntity.ok(usuario);
    }
}