package com.carlos.animeapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstadoAnime {
    EN_PRODUCCION("En producción"),
    EN_EMISION("En emision"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String estado;
}
