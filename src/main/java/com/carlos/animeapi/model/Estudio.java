package com.carlos.animeapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Entity
@Table(name = "estudios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("activo=true")
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = READ_ONLY)//PARA OCULTAR este atributo en el Swagger
    private Long id;

    private String nombre;

    private String pais;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Boolean activo = true;
}
