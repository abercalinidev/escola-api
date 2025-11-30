package com.br.escola_api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RepresentanteRequest {

    private String nome;

    private String sobrenome;

    private String celular;

    private String email;

    private String cpf;

    //@Valid
    private EnderecoRequest endereco;

}
