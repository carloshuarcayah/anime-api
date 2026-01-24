package com.carlos.animeapi.controller;

import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.service.EstudioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios")
public class EstudioController {
    @Autowired
    EstudioService estudioService;

    @GetMapping
    public List<Estudio> listarTodo(){
        return estudioService.listarTodo();
    }

    @GetMapping("/{id}")
    public Estudio estudioPorId(@PathVariable Long id){
        return estudioService.estudioPorId(id);
    }

    @PostMapping
    public Estudio crearEstudio(@RequestBody @Valid Estudio estudio){
        return estudioService.guardar(estudio);
    }

    @PutMapping("/{id}")
    public Estudio actualizarDatos(@PathVariable Long id,@RequestBody @Valid Estudio estudio){
        return estudioService.actualizar(id,estudio);
    }

    @DeleteMapping("/{id}")
    public void eliminarEstudio(@PathVariable Long id){
        estudioService.eliminarEstudio(id);
    }
}
