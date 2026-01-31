package com.carlos.animeapi.service;

import com.carlos.animeapi.dto.EstudioDTO;
import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.EstudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstudioService {
    private final EstudioRepository estudioRepository;

    public Page<EstudioDTO> listarTodo(Pageable pageable){
        return estudioRepository.findAll(pageable).map(this::aDTO);
    }

    public Page<EstudioDTO> listarActivos(Pageable pageable) {
        return estudioRepository.findAllByActivoTrue(pageable).map(this::aDTO);
    }

    public Page<EstudioDTO> buscarPorNombre(String nombre, Pageable pageable){
        return estudioRepository.findEstudioByNombreContainingIgnoreCase(nombre,pageable).map(this::aDTO);
    }

    public EstudioDTO buscarPorId(Long id){
        return estudioRepository.findById(id).map(this::aDTO).orElseThrow(()->new RecursoNoEncontradoException("No se encontro ningun estudio con ID: "+id));
    }

    public EstudioDTO crear(Estudio estudio){
        if(estudio.getActivo()==null){
            estudio.setActivo(true);
        }
        return aDTO(estudioRepository.save(estudio));
    }

    public EstudioDTO actualizar(Long id, Estudio nuevosDatosEstudio){
        return estudioRepository.findById(id).map(encontrado->{
            encontrado.setNombre(nuevosDatosEstudio.getNombre());
            encontrado.setPais(nuevosDatosEstudio.getPais());
            encontrado.setFechaCreacion(nuevosDatosEstudio.getFechaCreacion());

            return aDTO(estudioRepository.save(encontrado));
        }).orElseThrow(()->new RecursoNoEncontradoException("ID no encontrado: No existe un estudio con ID-"+id));
    }

    public EstudioDTO eliminar(Long id){
        Estudio estudio = estudioRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("NO existe estudio con ID: "+id));
        if(!estudio.getActivo()){
            return aDTO(estudio);
        }
        estudio.setActivo(false);
        return aDTO(estudioRepository.save(estudio));
    }

    public EstudioDTO habilitar(Long id){
        Estudio estudio = estudioRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("NO existe estudio con ID: "+id));
        if(estudio.getActivo()){
            return aDTO(estudio);
        }

        estudio.setActivo(true);
        return aDTO(estudioRepository.save(estudio));
    }

    private EstudioDTO aDTO(Estudio estudio){
        return new EstudioDTO(
                estudio.getId(),
                estudio.getNombre(),
                estudio.getPais(),
                estudio.getFechaCreacion()
        );
    }
}
