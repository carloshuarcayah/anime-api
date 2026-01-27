package com.carlos.animeapi.exception;

import jakarta.xml.bind.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class ManejadorGlobalExcepciones {
    //POR SI NOI SE ENCUENTRA ALGO QUE BUSCAMOS
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Object> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    //PARA LOS ERRORES DE LOS VALID
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarValidaciones(MethodArgumentNotValidException ex) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("excepcion", "Errores de validacion");
        ex.getBindingResult().getAllErrors().forEach(error -> {
            respuesta.put(((FieldError) error).getField(), "Datos no validos: " + error.getDefaultMessage());
        });
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }
    //PARA ERRORES DE PARAMETROS
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> manejarErrorDeTipo(MethodArgumentTypeMismatchException ex){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", ex.getMessage());
        return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
    }

    //PARA CUALQUIER OTRO ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarExcepcionesGenerales(Exception ex){
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("tipo_excepcion",ex.getClass().getSimpleName());
        respuesta.put("mensaje","Error inesperado: "+ex.getMessage());
        return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
