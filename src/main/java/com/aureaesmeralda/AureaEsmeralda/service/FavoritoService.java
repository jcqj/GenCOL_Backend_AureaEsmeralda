package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.model.Favorito;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.FavoritoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository,
                           UsuarioRepository usuarioRepository,
                           ProductoRepository productoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    public Favorito agregarFavorito(Long usuarioId, Long productoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con ID " + usuarioId + " no existe."));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + productoId + " no existe."));
        Favorito favorito = new Favorito(usuario, producto);
        return favoritoRepository.save(favorito);
    }

    public List<Favorito> obtenerFavoritosPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioIdUs(usuarioId);
    }

    public List<Favorito> obtenerFavoritosPorUsuarioOrdenados(Long usuarioId) {
        return favoritoRepository.findByUsuarioIdUsOrderByAgregadoEnDesc(usuarioId);
    }

    public boolean esFavorito(Long usuarioId, Long productoId) {
        return favoritoRepository.existsByUsuarioIdUsAndProductoId(usuarioId, productoId);
    }

    public Optional<Favorito> obtenerFavorito(Long usuarioId, Long productoId) {
        return favoritoRepository.findByUsuarioIdUsAndProductoId(usuarioId, productoId);
    }

    @Transactional
    public void eliminarFavorito(Long id) {
        Favorito favorito = favoritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Favorito con ID " + id + " no existe."));
        favoritoRepository.delete(favorito);
    }

    @Transactional
    public void eliminarFavoritoPorUsuarioYProducto(Long usuarioId, Long productoId) {
        int eliminados = favoritoRepository.deleteByUsuarioIdUsAndProductoId(usuarioId, productoId);
        if (eliminados == 0) {
            throw new IllegalArgumentException("No se encontró el favorito para el usuario " + usuarioId + " y producto " + productoId);
        }
    }
}
