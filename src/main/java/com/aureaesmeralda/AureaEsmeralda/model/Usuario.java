package com.aureaesmeralda.AureaEsmeralda.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios")
public class Usuario {
    //!        Creamos 'atributos' o 'columnas' para DB.

    //! ID incremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_us")
    private Long idUs;

    //! Nombre Usuario
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 5, max = 150, message = "Ingresa un nombre valido")
    @Column(name = "nombre_us",nullable = false, length = 150)
    private String nombreUs;

    //! TELEFONO Usuario
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 dígitos")
    @Pattern(regexp = "^\\+?[0-9]+$", message = "El teléfono solo puede contener números")
    @Column(name = "telefono_us", length = 15, nullable = false)
    private String telefonoUs;

    //! Email es nuestro identificador unico de login.
    //! CORREO Usuario
    @NotBlank(message = "Ingrese un correo valido")
    @Email(message = "Ingrese un correo valido")
    @Column(name = "correo_us", unique = true, nullable = false, length = 150)
    private String correoUs;

    //! CONTRASEÑA Usuario
    @NotBlank(message = "Ingrese una contraseña valida")
    @Size(min = 5, message = "La contraseña debe te tener al menos 5 caracteres")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial (@$!%*?&)"
    )
    @Column(name = "contrasena_us", nullable = false)
    private String contrasenaUs;

    //! ESTADO ACTIVO, inicializa en true
    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado_activo_us", nullable = false)
    private Boolean estadoActivoUs = true;

    //! ROL
    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "rol_us", nullable = false, length = 30)
    private String rolUs = "Usuario";

    //! CREADO EN
    @Column(name = "creado_en_us", nullable = false, updatable = false)
    private LocalDateTime creadoEnUs;

    //! Constructor Vacio.
    public Usuario(){}

    //! Método que se ejecuta automáticamente antes de guardar el usuario por primera vez
    @PrePersist
    protected void onCreate()
    {
        this.creadoEnUs = LocalDateTime.now();
    }

    //! ────────────────── GETTER Y SETTERS ──────────────────

    public Long getIdUs()
    {
        return idUs;
    }
    public void setIdUs(Long idUs)
    {
        this.idUs = idUs;
    }

    public String getNombreUs()
    {
        return nombreUs;
    }
    public void setNombreUs(String nombreUs)
    {
        this.nombreUs = nombreUs;
    }

    public String getTelefonoUs()
    {
        return telefonoUs;
    }
    public void setTelefonoUs(String telefonoUs) {
        this.telefonoUs = telefonoUs;
    }

    public String getCorreoUs()
    {
        return correoUs;
    }
    public void setCorreoUs(String correoUs)
    {
        this.correoUs = correoUs;
    }

    public String getContrasenaUs()
    {
        return contrasenaUs;
    }
    public void setContrasenaUs(String contrasenaUs)
    {
        this.contrasenaUs = contrasenaUs;
    }

    public Boolean getEstadoActivoUs()
    {
        return estadoActivoUs;
    }
    public void setEstadoActivoUs(Boolean estadoActivoUs)
    {
        this.estadoActivoUs = estadoActivoUs;
    }

    public String getRolUs()
    {
        return rolUs;
    }
    public void setRolUs(String rolUs)
    {
        this.rolUs = rolUs;
    }

    public LocalDateTime getCreadoEnUs()
    {
        return creadoEnUs;
    }
    public void setCreadoEnUs(LocalDateTime creadoEnUs)
    {
        this.creadoEnUs = creadoEnUs;
    }
}
