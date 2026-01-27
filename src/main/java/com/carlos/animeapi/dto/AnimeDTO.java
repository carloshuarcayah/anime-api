package com.carlos.animeapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AnimeDTO(
        Long id,
        String nombre,
        Integer capitulos,
        String estado,
        String categoria,
        String estudio,
        LocalDate primeraEmision,
        LocalDate ultimaEmision) {
}
