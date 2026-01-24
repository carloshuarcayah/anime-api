package com.carlos.animeapi.model;

public enum EstadoAnime {
    EN_PRODUCCION("En producción"),
    EN_EMISION("En emision"),
    FINALIZADO("Finalizado"),
    CANCELADO("Cancelado");

    private final String estado;

    EstadoAnime(String descripcion){
        this.estado = descripcion;
    }
}
