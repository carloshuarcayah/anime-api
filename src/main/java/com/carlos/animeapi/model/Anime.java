package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "animes")
@Data
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

    private String nombre;

    private int capitulos;

    //EN EMISION, FINALIZADO, CANCELADO
    private String estado;

    @Column(name = "primera_emision")
    private LocalDate primeraEmision;

    @Column(name = "ultima_emision")
    private LocalDate ultimaEmision;

    @ManyToOne
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;
}
