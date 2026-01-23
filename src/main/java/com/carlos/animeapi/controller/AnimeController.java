package com.carlos.animeapi.controller;

import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping
    public List<Anime> obtenerAnimes(){
        return animeService.listarTodo();
    }

    @GetMapping("/{id}")
    public Anime obtenerAnime(@PathVariable Long id){
        return animeService.animePorId(id);
    }

    @PostMapping
    public Anime crearAnime(@RequestBody Anime anime){
        return  animeService.guardar(anime);
    }

    @PutMapping("/{id}")
    public Anime actualizarAnime(@PathVariable long id,@RequestBody Anime anime) {
        return animeService.actualizar(id,anime);
    }

    @DeleteMapping("/{id}")
    public void eliminaAnime(@PathVariable Long id){
        animeService.eliminar(id);
    }
}