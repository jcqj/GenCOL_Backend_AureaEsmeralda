package com.aureaesmeralda.AureaEsmeralda.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;



@Entity
@Table(name = "certificados")
public class Certificado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cert")
    private Long id;

    //CÓDIGO DEL CERTIFICADO
    @NotBlank(message = "El codigo del certificado es obligatorio")
    @Column(name = "codigo_cert", unique = true, nullable = false, length = 100)
    private String codigo;

    //GEMOLOGO QUE CERTIFICA
    @NotBlank(message = "Nombre del gemologo es obligatorio")
    @Column(name = "nombre_gemologo", nullable = false, length = 150)
    private String nombreGemologo;

    //FECHA DE CERTIFICACION
    @NotNull(message = "La fecha de certificacion es obligatoria")
    @Column(name = "fecha_cert", nullable = false)
    private LocalDate fechaCert;

    //PDF CERTIFICADO
    @Column(name = "url_pdf_cert", length = 500)
    private String urlPdf;

    //IMAGEN
    @Column(name = "imagen_joya", length = 500)
    private String imagenJoya;

    //Conexion
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false, unique = true)
    @JsonBackReference
    private Producto producto;

    public Certificado () {}

    public Certificado(String codigo, String nombreGemologo, LocalDate fechaCert, String urlPdf, String imagenJoya, Producto producto) {
        this.codigo = codigo;
        this.nombreGemologo = nombreGemologo;
        this.fechaCert = fechaCert;
        this.urlPdf = urlPdf;
        this.imagenJoya = imagenJoya;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreGemologo() {
        return nombreGemologo;
    }

    public void setNombreGemologo(String nombreGemologo) {
        this.nombreGemologo = nombreGemologo;
    }

    public LocalDate getFechaCert() {
        return fechaCert;
    }

    public void setFechaCert(LocalDate fechaCert) {
        this.fechaCert = fechaCert;
    }

    public String getUrlPdf() {
        return urlPdf;
    }

    public void setUrlPdf(String urlPdf) {
        this.urlPdf = urlPdf;
    }

    public String getImagenJoya(){
        return imagenJoya;
    }

    public void setImagenJoya(String imagenJoya){
        this.imagenJoya = imagenJoya;
    }


    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



}
