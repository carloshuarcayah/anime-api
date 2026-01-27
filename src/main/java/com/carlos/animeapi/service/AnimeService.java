package com.carlos.animeapi.service;

import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.EstadoAnime;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.AnimeRepository;
import com.carlos.animeapi.repository.CategoriaRepository;
import com.carlos.animeapi.repository.EstudioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public Page<Anime> listarTodo(Pageable pageable){
        return animeRepository.findAll(pageable);
    }

    public Anime animePorId(Long id){
        return animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("Anime no encontrado, ID: "+id));
    }

    public Page<Anime> buscarPorNombre(String nombre, Pageable pageable){
        return animeRepository.findCategoriaByNombreContainingIgnoreCase(nombre, pageable);
    }

    public Anime guardar(Anime anime){
//
        if(anime.getActivo()==null){
            anime.setActivo(true);
        }
        if(anime.getEstado()==null)
            anime.setEstado(EstadoAnime.EN_PRODUCCION);

        validarCategoria_Estudio(anime);

        return animeRepository.save(anime);
    }

    private void validarCategoria_Estudio(Anime anime){
        if(anime.getEstudio()!=null && anime.getEstudio().getId()!=null){
            anime.setEstudio(estudioRepository.findById(
                    anime.getEstudio().getId()
                    ).orElseThrow(
                            ()->new RecursoNoEncontradoException("No se ha encontrado un estudio con ID: "+anime.getEstudio().getId())
                    )
            );
        }

        if(anime.getCategoria()!=null && anime.getCategoria().getId()!=null){
            anime.setCategoria(categoriaRepository.findById(
                    anime.getCategoria().getId()
                    ).orElseThrow(
                    ()->new RecursoNoEncontradoException("No se ha encontrado una categoria con ID: "+anime.getCategoria().getId())
                    )
            );
        }
    }

    //SE PASAN LOS NUEVOS QUE SE QUIEREN REEMPLAZAR EN UN ANIME
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

        if(nuevo.getEstudio()!=null)
            actual.setEstudio(nuevo.getEstudio());

        if(nuevo.getCategoria()!=null)
            actual.setCategoria(nuevo.getCategoria());
    }

    public Anime actualizar(Long id,Anime nuevosDatosAnime){
        return animeRepository.findById(id).map(existente->{
            actualizarDatos(nuevosDatosAnime,existente);
            validarCategoria_Estudio(existente);
            return animeRepository.save(existente);
        }).orElseThrow(()->new RecursoNoEncontradoException("No existe anime con ID: "+id));
    }

    public void eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        animeRepository.save(encontrado);
    }
}
