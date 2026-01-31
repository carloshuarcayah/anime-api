package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.CategoriaDTO;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> listarActivos(Pageable pageable) {
        return ResponseEntity.ok(categoriaService.listarActivos(pageable));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<CategoriaDTO>> listarTodas(Pageable pageable){
        return ResponseEntity.ok(categoriaService.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<CategoriaDTO>> buscarPorNombre(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre, pageable));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> crear(@RequestBody @Valid Categoria categoria){
        return ResponseEntity.ok(categoriaService.crear(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizar(@PathVariable long id, @RequestBody @Valid Categoria categoria){
        return ResponseEntity.ok(categoriaService.actualizar(id,categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.eliminar(id));
    }

    @PutMapping("/{id}/habilitar")
    public ResponseEntity<CategoriaDTO> habilitar(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.habilitar(id));
    }
}
