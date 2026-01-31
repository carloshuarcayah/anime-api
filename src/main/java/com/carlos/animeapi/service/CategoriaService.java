package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.CategoriaDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public Page<CategoriaDTO> listarTodos(Pageable pageable) {
        log.debug("Listando todas las categorias (incluye inactivas)");
        return categoriaRepository.findAll(pageable).map(this::aDTO);
    }


    public Page<CategoriaDTO> listarActivos(Pageable pageable) {
        log.debug("Listando solo categorias activas");
        return categoriaRepository.findAllByActivoTrue(pageable).map(this::aDTO);
    }


    public CategoriaDTO buscarPorId(Long id) {
        log.debug("Buscando categoria con ID: {}", id);
        return categoriaRepository.findById(id)
                .map(this::aDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada, ID: " + id));
    }


    public Page<CategoriaDTO> buscarPorNombre(String nombre, Pageable pageable) {
        log.debug("Buscando categorias con nombre: {}", nombre);
        return categoriaRepository.findCategoriaByNombreContainingIgnoreCase(nombre, pageable)
                .map(this::aDTO);
    }

    public CategoriaDTO crear(Categoria categoria) {
        log.info("Creando nueva categoria: {}", categoria.getNombre());

        if (categoria.getActivo() == null) {
            categoria.setActivo(true);
        }

        Categoria guardada = categoriaRepository.save(categoria);
        log.info("Categoria creada exitosamente con ID: {}", guardada.getId());

        return aDTO(guardada);
    }

    public CategoriaDTO actualizar(Long id, Categoria nuevosDatos) {
        log.info("Actualizando categoria con ID: {}", id);

        return categoriaRepository.findById(id)
                .map(existente -> {
                    if (nuevosDatos.getNombre() != null) {
                        existente.setNombre(nuevosDatos.getNombre());
                    }

                    Categoria actualizada = categoriaRepository.save(existente);

                    return aDTO(actualizada);
                }).orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));
    }

    public CategoriaDTO habilitar(Long id) {
        log.info("Habilitando categoria con ID: {}", id);

        Categoria encontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));

        if (encontrada.getActivo()) {
            log.warn("Intento de habilitar categoria ya activa: {}", id);
            throw new IllegalStateException("La categoria con ID " + id + " ya está habilitada");
        }

        encontrada.setActivo(true);
        Categoria habilitada = categoriaRepository.save(encontrada);
        log.info("Categoria habilitada exitosamente: {}", id);

        return aDTO(habilitada);
    }

    public CategoriaDTO eliminar(Long id) {
        log.info("Eliminando (soft delete) categoria con ID: {}", id);

        Categoria encontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));

        encontrada.setActivo(false);
        Categoria eliminada = categoriaRepository.save(encontrada);
        log.info("Categoria marcada como inactiva: {}", id);

        return aDTO(eliminada);
    }

    private CategoriaDTO aDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}