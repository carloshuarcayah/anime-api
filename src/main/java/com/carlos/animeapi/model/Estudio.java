package com.carlos.animeapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Entity
@Table(name = "estudios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("activo=true")
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = READ_ONLY)//PARA OCULTAR este atributo en el Swagger
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Column(length = 100,unique = true)
    private String nombre;

    @NotBlank(message = "Pais no puede estar vacío.")
    @Column(length = 50)
    private String pais;

    @Column(name = "fecha_creacion")
    @PastOrPresent(message = "La fecha no puede ser una fecha futura.")
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;
}
