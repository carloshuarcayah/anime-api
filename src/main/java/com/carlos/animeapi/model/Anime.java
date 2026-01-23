package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "animes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("activo=true")
public class Anime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    private String nombre;

    private Integer capitulos;

    //EN EMISION, FINALIZADO, CANCELADO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @Column(nullable = false)
    private Boolean activo = true;
}
