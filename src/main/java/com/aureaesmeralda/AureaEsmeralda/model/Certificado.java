package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificados")
public class Certificado {

    //! Llave primaria autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cert")
    private Long idCert;

    //! CLAVE FORÁNEA: Relación Uno a Uno con Producto (Mapeado a pd_id)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pd_id", nullable = false, unique = true)
    @NotNull(message = "El producto asociado es obligatorio")
    private Producto producto;

    //! Nombre del gemólogo que realiza la certificación
    @NotBlank(message = "El nombre del gemólogo es obligatorio")
    @Size(max = 150, message = "El nombre del gemólogo no puede superar los 150 caracteres")
    @Column(name = "nombre_gemologo_cert", nullable = false, length = 150)
    private String nombreGemologoCert;

    //! Fecha en la que se emite el certificado
    @NotNull(message = "La fecha de certificación es obligatoria")
    @Column(name = "creado_en_cert", nullable = false)
    private LocalDate creadoEnCert;

    //! Detalles técnicos o especificaciones de la joya
    @NotBlank(message = "Los detalles del certificado son obligatorios")
    @Size(max = 255, message = "Los detalles no pueden superar los 255 caracteres")
    @Column(name = "detalles_cert", nullable = false, length = 255)
    private String detallesCert;

    //! Código único de identificación del certificado
    @NotBlank(message = "El código del certificado es obligatorio")
    @Size(max = 100, message = "El código no puede superar los 100 caracteres")
    @Column(name = "codigo_cert", unique = true, nullable = false, length = 100)
    private String codigoCert;

    //! URL del archivo PDF digital del certificado
    @Size(max = 500, message = "La URL del archivo PDF no puede superar los 500 caracteres")
    @Column(name = "archivo_url_cert", length = 500)
    private String archivoUrlCert;

    //! URL de la imagen de la joya asociada al certificado
    @Size(max = 500, message = "La URL de la imagen no puede superar los 500 caracteres")
    @Column(name = "imagen_joya_cert", length = 500)
    private String imagenJoyaCert;

    //! Constructor vacío obligatorio para JPA
    public Certificado() {}

    //! Constructor personalizado con los atributos del diagrama
    public Certificado(Producto producto, String nombreGemologoCert, LocalDate creadoEnCert,
                       String detallesCert, String codigoCert, String archivoUrlCert, String imagenJoyaCert) {
        this.producto = producto;
        this.nombreGemologoCert = nombreGemologoCert;
        this.creadoEnCert = creadoEnCert;
        this.detallesCert = detallesCert;
        this.codigoCert = codigoCert;
        this.archivoUrlCert = archivoUrlCert;
        this.imagenJoyaCert = imagenJoyaCert;
    }

    //! ─────────────── GETTERS Y SETTERS ───────────────

    public Long getIdCert() { return idCert; }
    public void setIdCert(Long idCert) { this.idCert = idCert; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public String getNombreGemologoCert() { return nombreGemologoCert; }
    public void setNombreGemologoCert(String nombreGemologoCert) { this.nombreGemologoCert = nombreGemologoCert; }

    public LocalDate getCreadoEnCert() { return creadoEnCert; }
    public void setCreadoEnCert(LocalDate creadoEnCert) { this.creadoEnCert = creadoEnCert; }

    public String getDetallesCert() { return detallesCert; }
    public void setDetallesCert(String detallesCert) { this.detallesCert = detallesCert; }

    public String getCodigoCert() { return codigoCert; }
    public void setCodigoCert(String codigoCert) { this.codigoCert = codigoCert; }

    public String getArchivoUrlCert() { return archivoUrlCert; }
    public void setArchivoUrlCert(String archivoUrlCert) { this.archivoUrlCert = archivoUrlCert; }

    public String getImagenJoyaCert() { return imagenJoyaCert; }
    public void setImagenJoyaCert(String imagenJoyaCert) { this.imagenJoyaCert = imagenJoyaCert; }
}