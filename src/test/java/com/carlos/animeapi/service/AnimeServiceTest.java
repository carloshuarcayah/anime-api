package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.AnimeRequestDTO;
import com.carlos.animeapi.dto.AnimeResponseDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.model.Categoria;
import com.carlos.animeapi.model.EstadoAnime;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.AnimeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DisplayName("Tests para AnimeService")
@ExtendWith(MockitoExtension.class)
public class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeService;

//    private Anime anime;
//    private Categoria categoria;
//    private Estudio estudio;
//    private Pageable pageable;
    @Test
    @DisplayName("Deberia listar los animes paginados")
    void listarTodosLosAnimes(){
        //ESTUDIO DE PRUEBA
        Estudio estudio = Estudio.builder()
                .id(1L)
                .nombre("Pierrot")
                .pais("Japón")
                .fechaCreacion(LocalDate.of(1979,5,1))
                .activo(true)
                .build();

        //CATEGORIA DE PRUEBA
        Categoria categoria = Categoria.builder()
                .id(1L)
                .nombre("Shonen")
                .activo(true)
                .build();

        //ANIME DE PRUEBA
        Anime anime = Anime.builder()
                .id(1L)
                .nombre("Naruto")
                .capitulos(220)
                .estado(EstadoAnime.FINALIZADO)
                .primeraEmision(LocalDate.of(2002,10,3))
                .ultimaEmision(LocalDate.of(2007,2,8))
                .estudio(estudio)
                .categoria(categoria)
                .activo(true).build();

        //CONFIGURACIÓN DE LA PAGINACIÓN: NUMERO DE PAGINA Y TAMAÑO
        Pageable pageable = PageRequest.of(0,10);
        Page<Anime> animePage = new PageImpl<>(List.of(anime));
        when(animeRepository.findAll(pageable)).thenReturn(animePage);

        Page<AnimeResponseDTO> respuesta = animeService.listarActivos(pageable);

        assertNotNull(respuesta);
        assertEquals(1,respuesta.getContent().size());
        assertEquals("Naruto",respuesta.getContent().getFirst().nombre());
        assertEquals("Finalizado",respuesta.getContent().getFirst().estado().getEstado());

        verify(animeRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Encuentra el anime por el ID")
    void animePorId(){
        Long idValido = 1L;

        //ESTUDIO DE PRUEBA
        Estudio estudio = Estudio.builder()
                .id(idValido)
                .nombre("Pierrot")
                .pais("Japón")
                .fechaCreacion(LocalDate.of(1979,5,1))
                .activo(true)
                .build();

        //CATEGORIA DE PRUEBA
        Categoria categoria = Categoria.builder()
                .id(1L)
                .nombre("Shonen")
                .activo(true)
                .build();

        //ANIME DE PRUEBA
        Anime anime = Anime.builder()
                .id(1L)
                .nombre("Naruto")
                .capitulos(220)
                .estado(EstadoAnime.FINALIZADO)
                .primeraEmision(LocalDate.of(2002,10,3))
                .ultimaEmision(LocalDate.of(2007,2,8))
                .estudio(estudio)
                .categoria(categoria)
                .activo(true).build();

        when(animeRepository.findById(idValido)).thenReturn(Optional.of(anime));

        AnimeResponseDTO animeResponseDTO =animeService.buscarPorId(idValido);

        assertThat(animeResponseDTO).isNotNull();
        assertThat(animeResponseDTO.id()).isEqualTo(idValido);
        assertThat(animeResponseDTO.estado().getEstado()).isEqualTo(EstadoAnime.FINALIZADO.getEstado());
    }

    @Test
    @DisplayName("Lanza excepcion cuando no existe anime")
    void animeNoExiste(){
        Long idInexistente = 2L;
        when(animeRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(()->animeService.buscarPorId(idInexistente)).isInstanceOf(RecursoNoEncontradoException.class).hasMessageContaining("Anime no encontrado");

        verify(animeRepository,times(1)).findById(idInexistente);

    }
}
