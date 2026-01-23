package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;

@Entity
@Table(name = "estudios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLRestriction("activo=true")
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String pais;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    private Boolean activo = true;
}
