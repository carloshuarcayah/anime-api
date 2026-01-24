package com.carlos.animeapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable = false)
    @NotBlank(message = "ESte campo no puede estar vacio")
    private String nombre;

    private Integer capitulos;

    //EN EMISION, FINALIZADO, CANCELADO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EstadoAnime estado = EstadoAnime.EN_PRODUCCION;

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
    @NotNull(message = "El estado es obligatorio")
    @Builder.Default
    private Boolean activo = true;
}
