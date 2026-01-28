package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.AnimeDTO;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.service.AnimeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    @Autowired
    private AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<AnimeDTO>> listarAnimes(Pageable pageable){
        return ResponseEntity.ok(animeService.listarTodo(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTO> obtenerAnime(@PathVariable Long id){
        return ResponseEntity.ok(animeService.buscarPorId(id));
    }
    @GetMapping("/buscar")
    public ResponseEntity<Page<AnimeDTO>> buscarAnime(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(animeService.buscarPorNombre(nombre, pageable));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AnimeDTO> crearAnime(@RequestBody @Valid Anime anime){
        return  ResponseEntity.ok(animeService.guardar(anime));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeDTO> actualizarAnime(@PathVariable long id,@RequestBody Anime anime) {
        return ResponseEntity.ok(animeService.actualizar(id,anime));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaAnime(@PathVariable Long id){
        animeService.eliminar(id);
    }
}