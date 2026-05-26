package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Categoria;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByBestSellerTrue();
    List<Producto> findByDisponibilidadIgnoreCase(String disponibilidad);
    List<Producto> findByCantidadGreaterThan(Integer cantidad);
    List<Producto> findByDescripcionContainingIgnoreCase(String descripcion); //busqueda por texto en descripcion
    List<Producto> findByPrecioOriginalBetween(Double min, Double max); // rango de precios
    List<Producto> findByCategoriaAndBestSellerTrue(Categoria categoria); //filtro best seller
    List<Producto> findByCategoriaAndPrecioOriginalBetween(Categoria categoria, Double min, Double max); //filtro rango de precios
    List<Producto> findByDisponibilidadIgnoreCaseAndPrecioOriginalBetween(String disponibilidad, Double min, Double max); // disponibilidad y ranfo de precio
}
