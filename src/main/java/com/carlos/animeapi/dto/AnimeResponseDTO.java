package com.carlos.animeapi.dto;

import com.carlos.animeapi.model.EstadoAnime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AnimeResponseDTO(
        Long id,
        String nombre,
        Integer capitulos,
        EstadoAnime estado,
        String estudioNombre,
        String categoriaNombre,
        LocalDate primeraEmision,
        LocalDate ultimaEmision
) {

}
