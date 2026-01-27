package com.carlos.animeapi.repository;

import com.carlos.animeapi.model.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    Page<Anime> findCategoriaByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
