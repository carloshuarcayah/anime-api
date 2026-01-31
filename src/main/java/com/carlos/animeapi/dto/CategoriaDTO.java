package com.carlos.animeapi.dto;

import lombok.Builder;

@Builder
public record CategoriaDTO(
        Long id,
        String nombre
) {
}
