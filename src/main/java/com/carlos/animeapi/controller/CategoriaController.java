package com.carlos.animeapi.controller;

import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> obtenerCategorias(){
        return categoriaService.listarTodo();
    }

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaService.crear(categoria);
    }

    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable long id, @RequestBody Categoria categoria){
        return categoriaService.actualizar(id,categoria);
    }
}
