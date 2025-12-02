package com.br.escola_api.mapper;

import com.br.escola_api.dto.InformacaoGeralDTO;
import com.br.escola_api.model.InformacaoGeral;
import com.br.escola_api.request.InformacaoGeralRequest;
import org.mapstruct.Mapper;

import static com.br.escola_api.configs.ConstantesGlobais.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface InformacaoGeralMapper {

    InformacaoGeral toModel(InformacaoGeralRequest informacaoGeralRequest);

    InformacaoGeralDTO toDTO(InformacaoGeral informacaoGeral);

}
