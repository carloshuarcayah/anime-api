package com.carlos.animeapi.dto;

import com.carlos.animeapi.model.EstadoAnime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AnimeRequestDTO(
        @NotBlank String nombre,
        @NotNull @Min(0) Integer capitulos,
        EstadoAnime estado,
        @NotNull Long estudioId,
        @NotNull Long categoriaId,
        LocalDate primeraEmision,
        LocalDate ultimaEmision
) {
}
