package com.br.escola_api.dto;

public record AlunoAPPDTO(Long id,
                          String nome,
                          String sobrenome,
                          String serieAluno,
                          String situacao,
                          Integer idade) {
}
