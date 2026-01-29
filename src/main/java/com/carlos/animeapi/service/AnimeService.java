package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.AnimeDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.EstadoAnime;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.AnimeRepository;
import com.carlos.animeapi.repository.CategoriaRepository;
import com.carlos.animeapi.repository.EstudioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    private final EstudioRepository estudioRepository;

    private final CategoriaRepository categoriaRepository;


    public Page<AnimeDTO> listarTodo(Pageable pageable){
        return animeRepository.findAll(pageable).map(this::aDTO);
    }

    public AnimeDTO aDTO(Anime anime){
        return new AnimeDTO(anime.getId(),
                anime.getNombre(),
                anime.getCapitulos(),
                anime.getEstado().getEstado(),
                anime.getCategoria().getNombre(),
                anime.getEstudio().getNombre(),
                anime.getPrimeraEmision(),
                anime.getUltimaEmision());
    }

    public AnimeDTO buscarPorId(Long id){
        return animeRepository.findById(id).map(this::aDTO).orElseThrow(()->new RecursoNoEncontradoException("Anime no encontrado, ID: "+id));
    }

    public Page<AnimeDTO> buscarPorNombre(String nombre, Pageable pageable){
        return animeRepository.findAnimeByNombreContainingIgnoreCase(nombre, pageable).map(this::aDTO);
    }

    public AnimeDTO guardar(Anime anime){
//
        if(anime.getActivo()==null){
            anime.setActivo(true);
        }
        if(anime.getEstado()==null)
            anime.setEstado(EstadoAnime.EN_PRODUCCION);

        validarCategoria_Estudio(anime);

        Anime nuevo = animeRepository.save(anime);

        return aDTO(nuevo);
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

//    //SE PASAN LOS NUEVOS QUE SE QUIEREN REEMPLAZAR EN UN ANIME
    public void pasarNuevosDatos (Anime nuevo, Anime actual){
            actual.setNombre(nuevo.getNombre());
            actual.setCapitulos(nuevo.getCapitulos());
            actual.setEstado(nuevo.getEstado());
            actual.setPrimeraEmision(nuevo.getPrimeraEmision());
            actual.setUltimaEmision(nuevo.getUltimaEmision());
            actual.setEstudio(nuevo.getEstudio());
            actual.setCategoria(nuevo.getCategoria());
    }

    public AnimeDTO actualizar(Long id,Anime nuevosDatosAnime){
        return animeRepository.findById(id).map(existente->{
            pasarNuevosDatos(nuevosDatosAnime,existente);
            validarCategoria_Estudio(existente);
            Anime actualizado = animeRepository.save(existente);
            return aDTO(actualizado);
        }).orElseThrow(()->new RecursoNoEncontradoException("No existe anime con ID: "+id));
    }

    public void eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        animeRepository.save(encontrado);
    }
}
