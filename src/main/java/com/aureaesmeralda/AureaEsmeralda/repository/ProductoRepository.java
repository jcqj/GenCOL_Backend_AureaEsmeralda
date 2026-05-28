package com.aureaesmeralda.AureaEsmeralda.repository;

import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto , Long>{
    //! Buscar productos por categoría (ANILLOS, COLLARES, PULSERAS, ARETES)
    List<Producto> findByCategoriaPdIgnoreCase(String categoria);

    //! Filtrar solo los productos que están marcados como más vendidos
    List<Producto> findByBestSellerPdTrue();

    //! Filtrar productos que tengan stock disponible mayor a cero
    List<Producto> findByStockPdGreaterThan(Integer stock);
}
