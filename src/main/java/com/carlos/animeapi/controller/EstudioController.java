package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.EstudioDTO;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.service.EstudioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/estudios")
public class EstudioController {
    private final EstudioService estudioService;

    @GetMapping
    public ResponseEntity<Page<EstudioDTO>> listarActivos(Pageable pageable){
        return ResponseEntity.ok(estudioService.listarActivos(pageable));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<EstudioDTO>> listarTodo(Pageable pageable){
        return ResponseEntity.ok(estudioService.listarTodo(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(estudioService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<EstudioDTO>> buscarPorNombre(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(estudioService.buscarPorNombre(nombre, pageable));
    }

    @PostMapping
    public ResponseEntity<EstudioDTO>  crear(@RequestBody @Valid Estudio estudio){
        return ResponseEntity.ok(estudioService.crear(estudio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudioDTO> actualizar(@PathVariable Long id,@RequestBody @Valid Estudio estudio){
        return ResponseEntity.ok(estudioService.actualizar(id,estudio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstudioDTO>  eliminar(@PathVariable Long id){
        return ResponseEntity.ok(estudioService.eliminar(id));
    }

    @PutMapping("/{id}")
    public EstudioDTO habilitar(@PathVariable Long id){
        return estudioService.habilitar(id);
    }
}
