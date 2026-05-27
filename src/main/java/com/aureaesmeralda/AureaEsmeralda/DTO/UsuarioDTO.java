package com.aureaesmeralda.AureaEsmeralda.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioDTO {
    private Long idUs;
    private String nombreUs;
    private String telefonoUs;
    private String correoUs;
    private Boolean estadoActivoUs;
    private String rolUs;
    private LocalDateTime creadoEnUs;

    public UsuarioDTO() {}

    // Getters y Setters
    public Long getIdUs() { return idUs; }
    public void setIdUs(Long idUs) { this.idUs = idUs; }

    public String getNombreUs() { return nombreUs; }
    public void setNombreUs(String nombreUs) { this.nombreUs = nombreUs; }

    public String getTelefonoUs() { return telefonoUs; }
    public void setTelefonoUs(String telefonoUs) { this.telefonoUs = telefonoUs; }

    public String getCorreoUs() { return correoUs; }
    public void setCorreoUs(String correoUs) { this.correoUs = correoUs; }

    public Boolean getEstadoActivoUs() { return estadoActivoUs; }
    public void setEstadoActivoUs(Boolean estadoActivoUs) { this.estadoActivoUs = estadoActivoUs; }

    public String getRolUs() { return rolUs; }
    public void setRolUs(String rolUs) { this.rolUs = rolUs; }

    public LocalDateTime getCreadoEnUs() { return creadoEnUs; }
    public void setCreadoEnUs(LocalDateTime creadoEnUs) { this.creadoEnUs = creadoEnUs; }
}