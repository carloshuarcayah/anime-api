package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.AnimeDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.Categoria;
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

    public Page<AnimeDTO> listarTodos(Pageable pageable){
        return animeRepository.findAll(pageable).map(this::aDTO);
    }

    public Page<AnimeDTO> listarActivos(Pageable pageable){
        return animeRepository.findAllByActivoTrue(pageable).map(this::aDTO);
    }

    public AnimeDTO buscarPorId(Long id){
        return animeRepository.findById(id).map(this::aDTO).orElseThrow(()->new RecursoNoEncontradoException("Anime no encontrado, ID: "+id));
    }

    public Page<AnimeDTO> buscarPorNombre(String nombre, Pageable pageable){
        return animeRepository.findAnimeByNombreContainingIgnoreCase(nombre, pageable).map(this::aDTO);
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

    public AnimeDTO crear(Anime anime){

        if(anime.getActivo()==null)
            anime.setActivo(true);

        if(anime.getEstado()==null)
            anime.setEstado(EstadoAnime.EN_PRODUCCION);

        validarCategoria_Estudio(anime);

        return aDTO(animeRepository.save(anime));
    }

    //SE PASAN LOS NUEVOS QUE SE QUIEREN REEMPLAZAR EN UN ANIME
    private void actualizarDatos (Anime nuevo, Anime actual){

        if (nuevo.getEstudio() == null || nuevo.getEstudio().getId() == null) {
            throw new IllegalArgumentException("El objeto estudio y su ID son obligatorios.");
        }
        if (nuevo.getCategoria() == null || nuevo.getCategoria().getId() == null) {
            throw new IllegalArgumentException("El objeto categoria y su ID son obligatorios.");
        }

        Estudio estudio = estudioRepository.findById(nuevo.getEstudio().getId()).orElseThrow(()-> new RecursoNoEncontradoException("No existe un estudio con ID: "+nuevo.getEstudio().getId()));
        Categoria categoria = categoriaRepository.findById(nuevo.getCategoria().getId()).orElseThrow(()-> new RecursoNoEncontradoException("No existe una categoria con ID: "+nuevo.getCategoria().getId()));

        actual.setNombre(nuevo.getNombre());
        actual.setCapitulos(nuevo.getCapitulos());
        actual.setEstado(nuevo.getEstado());
        actual.setPrimeraEmision(nuevo.getPrimeraEmision());
        actual.setUltimaEmision(nuevo.getUltimaEmision());
        actual.setEstudio(estudio);
        actual.setCategoria(categoria);
    }

    public AnimeDTO actualizar(Long id,Anime nuevosDatosAnime){
        return animeRepository.findById(id).map(existente->{

            actualizarDatos(nuevosDatosAnime,existente);
            validarCategoria_Estudio(existente);

            return aDTO(animeRepository.save(existente));
        }).orElseThrow(()->new RecursoNoEncontradoException("No existe anime con ID: "+id));
    }

    public AnimeDTO habilitar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(true);
        return aDTO(animeRepository.save(encontrado));
    }

    public AnimeDTO eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        return  aDTO(animeRepository.save(encontrado));
    }
}
