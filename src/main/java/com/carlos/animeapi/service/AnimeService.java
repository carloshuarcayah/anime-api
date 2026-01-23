package com.carlos.animeapi.service;

import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listarTodo(){
        return animeRepository.findAll();
    }

    public Anime guardar(Anime anime){
        return animeRepository.save(anime);
    }

    public Anime actualizar(Long id,Anime nuevosDatosAnime){
        return animeRepository.findById(id).map(encontrado->{
            if(nuevosDatosAnime.getNombre()!=null)         encontrado.setNombre(nuevosDatosAnime.getNombre());
            if(nuevosDatosAnime.getCapitulos()>0)          encontrado.setCapitulos(nuevosDatosAnime.getCapitulos());
            if(nuevosDatosAnime.getEstado()!=null)         encontrado.setEstado(nuevosDatosAnime.getEstado());
            if(nuevosDatosAnime.getPrimeraEmision()!=null) encontrado.setPrimeraEmision(nuevosDatosAnime.getPrimeraEmision());
            if(nuevosDatosAnime.getUltimaEmision()!=null)  encontrado.setUltimaEmision(nuevosDatosAnime.getUltimaEmision());
            if(nuevosDatosAnime.getEstudio()!=null)        encontrado.setEstudio(nuevosDatosAnime.getEstudio());
            if(nuevosDatosAnime.getCategoria()!=null)      encontrado.setCategoria(nuevosDatosAnime.getCategoria());
            return animeRepository.save(encontrado);
        }).orElseThrow(()->new RuntimeException("No existe anime con ID: "+id));
    }

    public void eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RuntimeException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        animeRepository.save(encontrado);
    }
}
