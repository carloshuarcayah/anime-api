package com.carlos.animeapi.repository;

import com.carlos.animeapi.model.Estudio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudioRepository extends JpaRepository<Estudio,Long> {
    Page<Estudio> findEstudioByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
