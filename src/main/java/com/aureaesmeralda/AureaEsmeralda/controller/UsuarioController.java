package com.aureaesmeralda.AureaEsmeralda.controller;

import com.aureaesmeralda.AureaEsmeralda.DTO.UsuarioDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService servicioUsuario;

    public UsuarioController(UsuarioService servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    // POST: http://localhost:8080/api/usuarios/registro
    // Cuerpo JSON: { "nombreUs": "Ana García", "correoUs": "ana@email.com",
    //               "contrasenaUs": "Pass123!", "telefonoUs": "3001234567" }
    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrar(@RequestBody Usuario datos) {
        UsuarioDTO nuevoUsuario = servicioUsuario.registrar(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    // POST: http://localhost:8080/api/usuarios/login
    // Cuerpo JSON: { "correo": "ana@email.com", "contrasena": "Pass123!" }
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> iniciarSesion(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        if (correo == null || contrasena == null) {
            throw new IllegalArgumentException("El correo y la contraseña son obligatorios");
        }

        UsuarioDTO usuario = servicioUsuario.iniciarSesion(correo, contrasena);
        return ResponseEntity.ok(usuario);
    }

    // GET: http://localhost:8080/api/usuarios/1
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        UsuarioDTO usuario = servicioUsuario.obtenerPorId(id);
        return ResponseEntity.ok(usuario);
    }
}