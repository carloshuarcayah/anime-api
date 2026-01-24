package com.carlos.animeapi.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
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

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

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
    @Schema(accessMode = READ_ONLY)//PARA OCULTAR este atributo en el Swagger
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Este campo no puede estar vacio")
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
    @Builder.Default
    private Boolean activo = true;
}
