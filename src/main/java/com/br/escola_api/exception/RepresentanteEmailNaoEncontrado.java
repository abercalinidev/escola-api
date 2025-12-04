package com.br.escola_api.exception;

public class RepresentanteEmailNaoEncontrado extends RuntimeException {

    public RepresentanteEmailNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
