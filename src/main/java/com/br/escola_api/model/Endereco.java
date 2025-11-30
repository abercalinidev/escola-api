package com.br.escola_api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@Data
public class Endereco {

    private String cep;

    private String numero;

    private String rua;

    private String bairro;

    private String estado;

    private String complemento;

    private String cidade;

}
