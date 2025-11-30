package com.br.escola_api.model;

import com.br.escola_api.enuns.Situacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String sobrenome;

    private String celular;

    private String email;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @CreationTimestamp
    private LocalDate dataCadastro;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    @Embedded
    private Endereco endereco;

}
