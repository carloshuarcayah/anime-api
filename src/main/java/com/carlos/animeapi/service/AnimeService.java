package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.AnimeRequestDTO;
import com.carlos.animeapi.dto.AnimeResponseDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.AnimeRepository;
import com.carlos.animeapi.repository.CategoriaRepository;
import com.carlos.animeapi.repository.EstudioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    private final EstudioRepository estudioRepository;

    private final CategoriaRepository categoriaRepository;

    public AnimeResponseDTO aDTO(Anime anime){
        return new AnimeResponseDTO(
                anime.getId(),
                anime.getNombre(),
                anime.getCapitulos(),
                anime.getEstado(),
                anime.getEstudio().getNombre(),
                anime.getCategoria().getNombre(),
                anime.getPrimeraEmision(),
                anime.getUltimaEmision());
    }

    public Page<AnimeResponseDTO> listarTodos(Pageable pageable){
        return animeRepository.findAll(pageable).map(this::aDTO);
    }

    public Page<AnimeResponseDTO> listarActivos(Pageable pageable){
        return animeRepository.findAllByActivoTrue(pageable).map(this::aDTO);
    }

    public AnimeResponseDTO buscarPorId(Long id){
        return animeRepository.findById(id).map(this::aDTO).orElseThrow(()->new RecursoNoEncontradoException("Anime no encontrado, ID: "+id));
    }

    public Page<AnimeResponseDTO> buscarPorNombre(String nombre, Pageable pageable){
        return animeRepository.findAnimeByNombreContainingIgnoreCase(nombre, pageable).map(this::aDTO);
    }


    public AnimeResponseDTO crear(AnimeRequestDTO dto){

        Estudio estudio = estudioRepository.findById(dto.estudioId()).orElseThrow(
                ()->new RecursoNoEncontradoException("No se ha encontrado un estudio con ID: "+dto.estudioId())
        );

        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(
                ()->new RecursoNoEncontradoException("No se ha encontrado una categoria con ID: "+dto.categoriaId())
        );

        Anime anime = Anime.builder()
                .nombre(dto.nombre())
                .capitulos(dto.capitulos())
                .estado(dto.estado())
                .primeraEmision(dto.primeraEmision())
                .ultimaEmision(dto.ultimaEmision())
                .estudio(estudio)
                .categoria(categoria).build();

        return aDTO(animeRepository.save(anime));
    }

    //SE PASAN LOS NUEVOS QUE SE QUIEREN REEMPLAZAR EN UN ANIME
    private void actualizarDatos (AnimeRequestDTO dto, Anime actual){

        Estudio estudio = estudioRepository.findById(dto.estudioId()).orElseThrow(()-> new RecursoNoEncontradoException("No existe un estudio con ID: "+dto.estudioId()));
        Categoria categoria = categoriaRepository.findById(dto.categoriaId()).orElseThrow(()-> new RecursoNoEncontradoException("No existe una categoria con ID: "+dto.categoriaId()));

        actual.setNombre(dto.nombre());
        actual.setCapitulos(dto.capitulos());
        actual.setEstado(dto.estado());
        actual.setPrimeraEmision(dto.primeraEmision());
        actual.setUltimaEmision(dto.ultimaEmision());
        actual.setEstudio(estudio);
        actual.setCategoria(categoria);
    }

    public AnimeResponseDTO actualizar(Long id, AnimeRequestDTO dto){
        return animeRepository.findById(id).map(existente->{
            actualizarDatos(dto,existente);

            return aDTO(animeRepository.save(existente));
        }).orElseThrow(()->new RecursoNoEncontradoException("No existe anime con ID: "+id));
    }

    public AnimeResponseDTO habilitar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(true);
        return aDTO(animeRepository.save(encontrado));
    }

    public void eliminar(Long id){
        Anime encontrado = animeRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No existe un anime con ID: "+ id));
        encontrado.setActivo(false);
        aDTO(animeRepository.save(encontrado));
    }
}
