package com.carlos.animeapi.service;

import com.carlos.animeapi.exception.RecursoNoEncontradoException;
import com.carlos.animeapi.model.Estudio;
import com.carlos.animeapi.repository.EstudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudioService {
    @Autowired
    EstudioRepository estudioRepository;

    public Page<Estudio> listarTodo(Pageable pageable){
        return estudioRepository.findAll(pageable);
    }

    public Estudio estudioPorId(Long id){
        return estudioRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("No se encontro ningun estudio con ID: "+id));
    }

    public Estudio guardar(Estudio estudio){
        if(estudio.getActivo()==null){
            estudio.setActivo(true);
        }

        return estudioRepository.save(estudio);
    }

    public Estudio actualizar(Long id, Estudio nuevosDatosEstudio){
        return estudioRepository.findById(id).map(encontrado->{
            if(nuevosDatosEstudio.getNombre()!=null)        encontrado.setNombre(nuevosDatosEstudio.getNombre());
            if(nuevosDatosEstudio.getPais()!=null)          encontrado.setPais(nuevosDatosEstudio.getPais());
            if(nuevosDatosEstudio.getFechaCreacion()!=null) encontrado.setFechaCreacion(nuevosDatosEstudio.getFechaCreacion());

            return estudioRepository.save(encontrado);
        }).orElseThrow(()->new RecursoNoEncontradoException("ID no encontrado: No existe un estudio con ID-"+id));
    }

    public void eliminarEstudio(Long id){
        Estudio estudio = estudioRepository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("NO existe estudio con ID: "+id));
        estudio.setActivo(false);
        estudioRepository.save(estudio);
    }

}
