package com.carlos.animeapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "categorias")
@Data
@SQLRestriction("activo=true")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Boolean activo = true;
}