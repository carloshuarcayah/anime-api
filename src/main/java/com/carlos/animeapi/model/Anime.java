package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "animes")
@Data
@SQLRestriction("activo=true")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    private String nombre;

    private int capitulos;

    //EN EMISION, FINALIZADO, CANCELADO
    @Enumerated(EnumType.STRING)
    private EstadoAnime estado;

    @Column(name = "primera_emision")
    private LocalDate primeraEmision;

    @Column(name = "ultima_emision")
    private LocalDate ultimaEmision;

    @ManyToOne
    @JoinColumn(name = "estudio_id")
    private Estudio estudio;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    private boolean activo = true;
}
