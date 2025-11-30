package com.br.escola_api.exception.handler;

import com.br.escola_api.exception.RepresentanteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RepresentanteNaoEncontradoException.class)
    public ResponseEntity<?> exceptionHandlerRepresentante(RepresentanteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
