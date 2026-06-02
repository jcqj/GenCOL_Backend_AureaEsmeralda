package com.aureaesmeralda.AureaEsmeralda.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistroRequest {

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 5, max = 150, message = "Ingresa un nombre valido")
    private String nombreUs;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "El teléfono solo puede contener números")
    private String telefonoUs;

    @NotBlank(message = "Ingrese un correo valido")
    @Email(message = "Ingrese un correo valido")
    private String correoUs;

    @NotBlank(message = "Ingrese una contraseña valida")
    @Size(min = 5, message = "La contraseña debe te tener al menos 5 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", 
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial (@$!%*?&)")
    private String contrasenaUs;

    // Getters y Setters
    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getTelefonoUs() {
        return telefonoUs;
    }

    public void setTelefonoUs(String telefonoUs) {
        this.telefonoUs = telefonoUs;
    }

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
