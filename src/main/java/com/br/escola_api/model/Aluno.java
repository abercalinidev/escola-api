package com.br.escola_api.model;

import com.br.escola_api.enuns.SerieAluno;
import com.br.escola_api.enuns.Situacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private Integer idade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    private SerieAluno serieAluno;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @ManyToMany
    @JoinTable(
            name = "aluno_representante",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "representante_id")
    )
    private List<Representante> representantes = new ArrayList<>();

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<InformacaoGeral> informacoes;
}
