package com.br.escola_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record RepresentanteDTO(Long id,
                               String nome,
                               String sobrenome,
                               String celular,
                               String email,
                               String cpf,
                               String situacao,
                               @JsonFormat(pattern = "dd/MM/yyyy")
                               LocalDate dataCadastro,
                               EnderecoDTO endereco) {
}
