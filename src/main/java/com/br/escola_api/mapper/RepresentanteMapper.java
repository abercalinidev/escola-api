package com.br.escola_api.mapper;

import com.br.escola_api.dto.RepresentanteDTO;
import com.br.escola_api.model.Representante;
import com.br.escola_api.request.RepresentanteRequest;
import org.mapstruct.Mapper;

import static com.br.escola_api.configs.ConstantesGlobais.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface RepresentanteMapper {

    Representante toModel(RepresentanteRequest representanteRequest);

    RepresentanteDTO toDTO(Representante representante);

}
