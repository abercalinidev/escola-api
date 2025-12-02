package com.br.escola_api.request;

import com.br.escola_api.enuns.SituacaoAlmoco;
import com.br.escola_api.enuns.SituacaoCafeManha;
import com.br.escola_api.enuns.SituacaoCafeTarde;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InformacaoGeralRequest {

    @Enumerated(EnumType.STRING)
    private SituacaoCafeManha situacaoCafeManha;

    @Enumerated(EnumType.STRING)
    private SituacaoAlmoco situacaoAlmoco;

    @Enumerated(EnumType.STRING)
    private SituacaoCafeTarde situacaoCafeTarde;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    private String observacao;

    //@Valid
    private AlunoInformacaoRequest aluno;

}
