package com.br.escola_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record InformacaoGeralDTO(Long id,
                                 String situacaoCafeManha,
                                 String situacaoAlmoco,
                                 String situacaoCafeTarde,
                                    @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataCadastro,
                                 String observacao,
                                 AlunoInformacaoDTO aluno) {
}
