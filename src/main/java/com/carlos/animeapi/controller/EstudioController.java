package com.carlos.animeapi.controller;

import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.service.EstudioService;
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

    @PostMapping
    public Estudio crearEStudio(@RequestBody Estudio estudio){
        return estudioService.guardar(estudio);
    }

    @PutMapping("/{id}")
    public Estudio actualizarDatos(@PathVariable Long id,@RequestBody Estudio estudio){
        return estudioService.actualizar(id,estudio);
    }
}
