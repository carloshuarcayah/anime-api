package com.carlos.animeapi.controller;

import com.carlos.animeapi.dto.CategoriaDTO;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<Page<CategoriaDTO>> obtenerCategorias(Pageable pageable){
        return ResponseEntity.ok(categoriaService.listarTodo(pageable));
    }

    @GetMapping("/{id}")
    public CategoriaDTO obtenerCategoria(@PathVariable Long id){
        return categoriaService.categoriaPorId(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<CategoriaDTO>> buscarCategoria(@RequestParam String nombre, Pageable pageable){
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre, pageable));
    }

    @PostMapping
    public CategoriaDTO crearCategoria(@RequestBody @Valid Categoria categoria){
        return categoriaService.crear(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaDTO actualizarCategoria(@PathVariable long id, @RequestBody @Valid Categoria categoria){
        return categoriaService.actualizar(id,categoria);
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
    }
}
