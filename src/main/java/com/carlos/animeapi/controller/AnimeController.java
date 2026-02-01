package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.AnimeRequestDTO;
import com.carlos.animeapi.dto.AnimeResponseDTO;
import com.carlos.animeapi.service.AnimeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<Page<AnimeResponseDTO>> listarActivos(Pageable pageable){
        return ResponseEntity.ok(animeService.listarActivos(pageable));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<AnimeResponseDTO>> listarTodos(Pageable pageable){
        return ResponseEntity.ok(animeService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeResponseDTO> obtenerAnime(@PathVariable Long id){
        return ResponseEntity.ok(animeService.buscarPorId(id));
    }
    @GetMapping("/buscar")
    public ResponseEntity<Page<AnimeResponseDTO>> buscarAnime(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(animeService.buscarPorNombre(nombre, pageable));
    }

    @PostMapping
    public ResponseEntity<AnimeResponseDTO> crearAnime(@RequestBody @Valid AnimeRequestDTO anime){
        return  ResponseEntity.status(HttpStatus.CREATED).body(animeService.crear(anime));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimeResponseDTO> actualizarAnime(@PathVariable Long id, @RequestBody @Valid AnimeRequestDTO anime) {
        return ResponseEntity.ok(animeService.actualizar(id,anime));
    }
    @PutMapping("/{id}/habilitar")
    public ResponseEntity<AnimeResponseDTO> habilitarAnime(@PathVariable Long id){
        return ResponseEntity.ok(animeService.habilitar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaAnime(@PathVariable Long id){
        animeService.eliminar(id);
    }
}