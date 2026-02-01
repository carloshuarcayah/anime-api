package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.CategoriaDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public Page<CategoriaDTO> listarTodos(Pageable pageable) {
        return categoriaRepository.findAll(pageable).map(this::aDTO);
    }


    public Page<CategoriaDTO> listarActivos(Pageable pageable) {
        return categoriaRepository.findAllByActivoTrue(pageable).map(this::aDTO);
    }


    public CategoriaDTO buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(this::aDTO)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada, ID: " + id));
    }


    public Page<CategoriaDTO> buscarPorNombre(String nombre, Pageable pageable) {
        return categoriaRepository.findCategoriaByNombreContainingIgnoreCase(nombre, pageable)
                .map(this::aDTO);
    }

    public CategoriaDTO crear(CategoriaDTO dto) {

        Categoria categoria = Categoria.builder()
                .nombre(dto.nombre())
                .activo(true).build();

        return aDTO(categoriaRepository.save(categoria));
    }

    public CategoriaDTO actualizar(Long id, CategoriaDTO dto) {

        return categoriaRepository.findById(id)
                .map(existente -> {
                    if (dto.nombre() != null) {
                        existente.setNombre(dto.nombre());
                    }

                    return aDTO(categoriaRepository.save(existente));
                }).orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));
    }

    public CategoriaDTO habilitar(Long id) {
        Categoria encontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));

        // SI ESTA Habilitado
        if (encontrada.getActivo()) {
            return aDTO(encontrada);
        }
        //EN CASO NO ESTE HABILITADO
        encontrada.setActivo(true);
        return aDTO(categoriaRepository.save(encontrada));
    }

    public void eliminar(Long id) {
        Categoria encontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe categoria con ID: " + id));

        encontrada.setActivo(false);
        categoriaRepository.save(encontrada);
    }

    private CategoriaDTO aDTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}