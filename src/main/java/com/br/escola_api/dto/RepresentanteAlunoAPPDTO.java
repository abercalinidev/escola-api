package com.br.escola_api.dto;

import java.util.List;

public record RepresentanteAlunoAPPDTO(Long id,
                                       String nome,
                                       String sobrenome,
                                       String email,
                                       String situacao,
                                       List<AlunoAPPDTO> alunos) {
}
