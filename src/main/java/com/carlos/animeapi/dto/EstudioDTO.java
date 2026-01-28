package com.carlos.animeapi.dto;

import java.time.LocalDate;

public record EstudioDTO(
        Long id,
        String nombre,
        String pais,
        LocalDate fechaCreacion
) {
}
