package com.br.escola_api.mapper;

import com.br.escola_api.dto.RepresentanteAlunoAPPDTO;
import com.br.escola_api.model.Representante;
import org.mapstruct.Mapper;

import static com.br.escola_api.configs.ConstantesGlobais.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface RepresentanteAlunoAPPMApper {

    RepresentanteAlunoAPPDTO toDTO(Representante representante);

}
