package com.br.escola_api.request;

import com.br.escola_api.enuns.SerieAluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AlunoRequest {

    private String nome;

    private String sobrenome;

    private Integer idade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private SerieAluno serieAluno;

    private List<AlunoRepresentante> representantes;

    //@Valid
    private EnderecoRequest endereco;
}
