package com.aureaesmeralda.AureaEsmeralda.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoria;

    @Column(length = 500)
    private String descripcion;
    private Double precioOriginal;
    private Integer descuento;
    private Integer cantidad;
    private String disponibilidad;
    private Boolean bestSeller;
    

}
