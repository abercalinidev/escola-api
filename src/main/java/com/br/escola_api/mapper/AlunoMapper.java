package com.br.escola_api.mapper;

import com.br.escola_api.dto.AlunoDTO;
import com.br.escola_api.model.Aluno;
import com.br.escola_api.request.AlunoRequest;
import org.mapstruct.Mapper;

import static com.br.escola_api.configs.ConstantesGlobais.SPRING_FRAMEWORK;

@Mapper(componentModel = SPRING_FRAMEWORK)
public interface AlunoMapper {

    Aluno toModel(AlunoRequest alunoRequest);

    AlunoDTO toDTO(Aluno aluno);

}
