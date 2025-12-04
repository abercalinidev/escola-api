package com.br.escola_api.exception.handler;

import com.br.escola_api.exception.AlunoNaoEcontradoException;
import com.br.escola_api.exception.InformacaoGeralNaoEncontradoExcpetion;
import com.br.escola_api.exception.RepresentanteEmailNaoEncontrado;
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

    @ExceptionHandler(AlunoNaoEcontradoException.class)
    public ResponseEntity<?> exceptionHandlerAluno(AlunoNaoEcontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InformacaoGeralNaoEncontradoExcpetion.class)
    public ResponseEntity<?> exceptionHandlerInformacaoGeral(InformacaoGeralNaoEncontradoExcpetion ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RepresentanteEmailNaoEncontrado.class)
    public ResponseEntity<?> exceptionHandlerEmailNaoEncontrado(RepresentanteEmailNaoEncontrado ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
