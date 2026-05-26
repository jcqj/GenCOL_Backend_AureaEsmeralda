package com.aureaesmeralda.AureaEsmeralda.service;

import com.aureaesmeralda.AureaEsmeralda.DTO.FavoritoDTO;
import com.aureaesmeralda.AureaEsmeralda.model.Favorito;
import com.aureaesmeralda.AureaEsmeralda.model.Producto;
import com.aureaesmeralda.AureaEsmeralda.model.Usuario;
import com.aureaesmeralda.AureaEsmeralda.repository.FavoritoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.ProductoRepository;
import com.aureaesmeralda.AureaEsmeralda.repository.UsuarioRepository;
import com.aureaesmeralda.AureaEsmeralda.util.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    // Reparado: Asignación correcta en el constructor
    public FavoritoService(FavoritoRepository favoritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public FavoritoDTO agregarFavorito(Long usuarioId, Long productoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario con ID " + usuarioId + " no existe."));

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + productoId + " no existe."));

        // Reparado: Uso seguro mediante setters para evitar fallas de constructor
        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setProducto(producto);

        Favorito favoritoGuardado = favoritoRepository.save(favorito);
        return MapperUtil.toFavoritoDTO(favoritoGuardado);
    }

    @Transactional(readOnly = true)
    public List<FavoritoDTO> obtenerFavoritosPorUsuario(Long usuarioId) {
        List<Favorito> favoritos = favoritoRepository.findByUsuarioIdUs(usuarioId);
        return favoritos.stream()
                .map(MapperUtil::toFavoritoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarFavorito(Long id) {
        Favorito favorito = favoritoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Favorito con ID " + id + " no existe."));
        favoritoRepository.delete(favorito);
    }
}