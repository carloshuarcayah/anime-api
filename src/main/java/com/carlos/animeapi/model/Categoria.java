package com.carlos.animeapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Entity
@Table(name = "categorias")
@Data
@SQLRestriction("activo=true")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = READ_ONLY)//PARA OCULTAR este atributo en el Swagger
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Este campo no puede estar vacio.")
    private String nombre;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}