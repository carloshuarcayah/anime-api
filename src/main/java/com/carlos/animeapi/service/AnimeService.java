package com.carlos.animeapi.service;

import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.AnimeRepository;
import com.carlos.animeapi.repository.CategoriaRepository;
import com.carlos.animeapi.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    @Autowired
    private EstudioRepository estudioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Anime> listarTodo(){
        return animeRepository.findAll();
    }

    public Anime animePorId(Long id){
        return animeRepository.findById(id).orElseThrow(()->new RuntimeException("Anime no encontrado, ID: "+id));
    }

    public Anime guardar(Anime anime){
        return animeRepository.save(anime);
    }

    public void actualizarDatos (Anime nuevo, Anime actual){
        if(nuevo.getNombre()!=null)
            actual.setNombre(nuevo.getNombre());

        if(nuevo.getCapitulos()!=null && nuevo.getCapitulos()>=0)
            actual.setCapitulos(nuevo.getCapitulos());

        if(nuevo.getEstado()!=null)
            actual.setEstado(nuevo.getEstado());

        if(nuevo.getPrimeraEmision()!=null)
            actual.setPrimeraEmision(nuevo.getPrimeraEmision());

        if(nuevo.getUltimaEmision()!=null)
            actual.setUltimaEmision(nuevo.getUltimaEmision());

        if(nuevo.getEstudio()!=null && nuevo.getEstudio().getId()!=null)
            actual.setEstudio(
                    estudioRepository.findById(
                            nuevo.getEstudio().getId()
                    ).orElseThrow(()->new RuntimeException("Estudio no encontrado")));

        if(nuevo.getCategoria()!=null && nuevo.getCategoria().getId()!= null)
            actual.setCategoria(
                    categoriaRepository.findById(nuevo.getCategoria().getId()
                    ).orElseThrow(()->new RuntimeException("Categoria no encontrada")));
    }

    public Anime actualizar(Long id,Anime nuevosDatosAnime){
        return animeRepository.findById(id).map(existente->{
            actualizarDatos(nuevosDatosAnime,existente);
            return animeRepository.save(existente);
        }).orElseThrow(()->new RuntimeException("No existe anime con ID: "+id));
    }

    public void eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RuntimeException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        animeRepository.save(encontrado);
    }
}
