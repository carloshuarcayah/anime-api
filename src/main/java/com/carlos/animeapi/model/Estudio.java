package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "estudios")
@Data
public class Estudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String pais;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
