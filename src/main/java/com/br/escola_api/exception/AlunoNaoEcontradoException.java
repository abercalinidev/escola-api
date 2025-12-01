package com.br.escola_api.exception;

public class AlunoNaoEcontradoException extends RuntimeException {

    public AlunoNaoEcontradoException(String mensagem) {
        super(mensagem);
    }
}
