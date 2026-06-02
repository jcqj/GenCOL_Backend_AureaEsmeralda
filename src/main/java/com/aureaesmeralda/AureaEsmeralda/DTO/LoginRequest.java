package com.aureaesmeralda.AureaEsmeralda.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Ingrese un correo valido")
    @Email(message = "Ingrese un correo valido")
    private String correoUs;

    @NotBlank(message = "Ingrese una contraseña valida")
    private String contrasenaUs;

    // Getters y Setters
    public String getCorreoUs() {
        return correoUs;
    }

    public void setCorreoUs(String correoUs) {
        this.correoUs = correoUs;
    }

    public String getContrasenaUs() {
        return contrasenaUs;
    }

    public void setContrasenaUs(String contrasenaUs) {
        this.contrasenaUs = contrasenaUs;
    }
}
