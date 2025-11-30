package com.br.escola_api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EnderecoRequest {

    private String cep;

    private String rua;

    private String bairro;

    private String cidade;

    private String numero;

    private String estado;

    private String complemento;

}
