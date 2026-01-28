package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.EstudioDTO;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.service.EstudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios")
public class EstudioController {
    @Autowired
    EstudioService estudioService;

    @GetMapping
    public ResponseEntity<Page<EstudioDTO>> listarTodo(Pageable pageable){
        return ResponseEntity.ok(estudioService.listarTodo(pageable));
    }

    @GetMapping("/{id}")
    public EstudioDTO estudioPorId(@PathVariable Long id){
        return estudioService.estudioPorId(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<EstudioDTO>> buscarEstudio(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(estudioService.buscarEstudio(nombre, pageable));
    }

    @PostMapping
    public EstudioDTO crearEstudio(@RequestBody @Valid Estudio estudio){
        return estudioService.guardar(estudio);
    }

    @PutMapping("/{id}")
    public EstudioDTO actualizarDatos(@PathVariable Long id,@RequestBody @Valid Estudio estudio){
        return estudioService.actualizar(id,estudio);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstudio(@PathVariable Long id){
        estudioService.eliminarEstudio(id);
    }
}
