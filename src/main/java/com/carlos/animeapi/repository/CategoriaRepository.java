package com.carlos.animeapi.repository;

import com.carlos.animeapi.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Page<Categoria> findAllByActivoTrue(Pageable pageable);
    Page<Categoria> findCategoriaByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
