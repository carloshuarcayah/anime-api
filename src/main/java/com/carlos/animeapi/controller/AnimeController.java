package com.carlos.animeapi.controller;

import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
