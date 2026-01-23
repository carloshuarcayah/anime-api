package com.carlos.animeapi.service;

import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarTodo(){
        return categoriaRepository.findAll();
    }

    public Categoria crear(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    //Con el id y los datos nuevos recibidos, buscamos si existe el id y actualizamos los datos enviados
    public Categoria actualizar(Long id,Categoria nuevosDatosCategoria){
        return categoriaRepository.findById(id).map(encontrado->{
            if(nuevosDatosCategoria.getNombre()!=null) encontrado.setNombre(nuevosDatosCategoria.getNombre());
            return categoriaRepository.save(encontrado);
        }).orElseThrow(()->new RuntimeException("No existe anime con el id: "+id));
    }

    public void eliminarCategoria(Long id){
        Categoria encontrado = categoriaRepository.findById(id).orElseThrow(()-> new RuntimeException("No existe una categoria con ID: "+id));
        encontrado.setActivo(false);
        categoriaRepository.save(encontrado);
    }

}
