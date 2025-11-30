package com.br.escola_api.dto;

public record EnderecoDTO(String cep,
                          String numero,
                          String rua,
                          String cidade,
                          String bairro,
                          String complemento,
                          String estado) {
}