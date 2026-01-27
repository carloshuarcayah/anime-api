package com.carlos.animeapi.service;

import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Page<Categoria> listarTodo(Pageable pageable){
        return categoriaRepository.findAll(pageable);
    }

    public Page<Categoria> buscarCategoria(String nombre, Pageable pageable){
        return categoriaRepository.findCategoriaByNombreContainingIgnoreCase(nombre, pageable);
    }

    public Categoria categoriaPorId(Long id){
        return categoriaRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No se encontro ninguna categoria con ID: "+id));
    }

    public Categoria crear(Categoria categoria){
        if(categoria.getActivo()==null){
            categoria.setActivo(true);
        }

        return categoriaRepository.save(categoria);
    }

    //Con el id y los datos nuevos recibidos, buscamos si existe el id y actualizamos los datos enviados
    public Categoria actualizar(Long id,Categoria nuevosDatosCategoria){
        return categoriaRepository.findById(id).map(encontrado->{
            if(nuevosDatosCategoria.getNombre()!=null) encontrado.setNombre(nuevosDatosCategoria.getNombre());
            return categoriaRepository.save(encontrado);
        }).orElseThrow(()->new RecursoNoEncontradoException("No existe categoria con el id: "+id));
    }

    public void eliminarCategoria(Long id){
        Categoria encontrado = categoriaRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("No existe una categoria con ID: "+id));
        encontrado.setActivo(false);
        categoriaRepository.save(encontrado);
    }

}
