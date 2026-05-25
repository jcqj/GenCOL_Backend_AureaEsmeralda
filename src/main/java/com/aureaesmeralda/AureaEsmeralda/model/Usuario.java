package com.aureaesmeralda.AureaEsmeralda.model;


import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_us")
    private Long id_us;

    //!        Creamos 'atributos' o 'columnas' para DB.

    @Column(nullable = false)
    private String nombre_us; //! Nombre Usuario

    @Column(nullable = false)
    private String telefono_us; //! Telefono Usuario

    //! Email es nuestro identificador unico de login.
    @Column(unique = true, nullable = false)
    private String correo_us; //! Correo Usuario

    @Column(nullable = false)
    private String contrasena_us; //! Contraseña Usuario

    @Column(nullable = false)

}
