package com.br.escola_api.model;

import com.br.escola_api.enuns.SituacaoAlmoco;
import com.br.escola_api.enuns.SituacaoCafeManha;
import com.br.escola_api.enuns.SituacaoCafeTarde;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table
@Entity
public class InformacaoGeral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SituacaoCafeManha situacaoCafeManha;

    @Enumerated(EnumType.STRING)
    private SituacaoAlmoco situacaoAlmoco;

    @Enumerated(EnumType.STRING)
    private SituacaoCafeTarde situacaoCafeTarde;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime dataCadastro;

    private String observacao;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

}
