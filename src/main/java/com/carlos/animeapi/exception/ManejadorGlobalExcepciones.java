package com.carlos.animeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class ManejadorGlobalExcepciones {
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje",ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionesGenerales(Exception ex){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje","Error inesperado: "+ex.getMessage());
        return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
