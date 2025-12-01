package com.br.escola_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record AlunoDTO(Long id,
                       String nome,
                       String sobrenome,
                       Integer idade,
                       @JsonFormat(pattern = "dd/MM/yyyy")
                       LocalDate dataNascimento,
                       String situacao,
                       String serieAluno,
                       EnderecoDTO endereco,
                       List<AlunoRepresentanteDTO> representantes
                       ) {
}
