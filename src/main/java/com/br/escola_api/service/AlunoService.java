package com.br.escola_api.service;

import com.br.escola_api.dto.AlunoDTO;
import com.br.escola_api.exception.AlunoNaoEcontradoException;
import com.br.escola_api.exception.RepresentanteNaoEncontradoException;
import com.br.escola_api.mapper.AlunoMapper;
import com.br.escola_api.model.Aluno;
import com.br.escola_api.model.Representante;
import com.br.escola_api.repository.AlunoRepository;
import com.br.escola_api.repository.RepresentanteRepository;
import com.br.escola_api.request.AlunoRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.br.escola_api.configs.ConstantesGlobais.ALUNO_NAO_ENCONTRADO;
import static com.br.escola_api.configs.ConstantesGlobais.REPRESENTANTE_NAO_ENCONTRADO;
import static com.br.escola_api.enuns.Situacao.ATIVO;
import static com.br.escola_api.enuns.Situacao.INATIVO;

@Service
@Slf4j
public class AlunoService {

    private AlunoRepository alunoRepository;

    private AlunoMapper alunoMapper;

    private RepresentanteRepository representanteRepository;

    public AlunoService(AlunoRepository alunoRepository,
                        RepresentanteRepository representanteRepository,
                        AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.representanteRepository = representanteRepository;
        this.alunoMapper = alunoMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Transactional
    public AlunoDTO salvar(AlunoRequest alunoRequest) {
        logger.info("Iniciando o processo para salvar Aluno {}", alunoRequest);

        var representantes = new ArrayList<Representante>();
        var aluno = alunoMapper.toModel(alunoRequest);
        aluno.setSituacao(ATIVO);

        alunoRequest
                .getRepresentantes()
                .forEach(r -> {
                    var representante = representanteRepository.findById(r.getId())
                            .orElseThrow(() -> new RepresentanteNaoEncontradoException(
                                    String.format(REPRESENTANTE_NAO_ENCONTRADO, r.getId())));

                    representantes.add(representante);
                });

        aluno.setRepresentantes(representantes);
        alunoRepository.save(aluno);

        logger.info("Aluno salvo com sucesso");

        return alunoMapper.toDTO(aluno);
    }

    @Transactional
    public AlunoDTO editar(AlunoRequest alunoRequest, Long alunoId) {

        logger.info("Iniciando edição do aluno ID: {} com dados: {}", alunoId, alunoRequest);

        Aluno aluno = verificarAluno(alunoId);

        List<Representante> representantes = alunoRequest.getRepresentantes()
                .stream()
                .map(r -> representanteRepository.findById(r.getId())
                        .orElseThrow(() ->
                                new RepresentanteNaoEncontradoException(
                                        String.format(REPRESENTANTE_NAO_ENCONTRADO, r.getId())
                                )
                        )
                ).toList();

        aluno.setRepresentantes(representantes);

        if (alunoRequest.getEndereco() != null) {
            aluno.getEndereco().setBairro(alunoRequest.getEndereco().getBairro());
            aluno.getEndereco().setCep(alunoRequest.getEndereco().getCep());
            aluno.getEndereco().setCidade(alunoRequest.getEndereco().getCidade());
            aluno.getEndereco().setNumero(alunoRequest.getEndereco().getNumero());
            aluno.getEndereco().setEstado(alunoRequest.getEndereco().getEstado());
            aluno.getEndereco().setRua(alunoRequest.getEndereco().getRua());
            aluno.getEndereco().setComplemento(alunoRequest.getEndereco().getComplemento());
        }

        BeanUtils.copyProperties(
                alunoRequest,
                aluno,
                "id", "situacao");

        logger.info("Aluno ID {} editado com sucesso.", aluno.getId());

        return alunoMapper.toDTO(aluno);
    }

    public List<AlunoDTO> listarAlunos() {
        logger.info("Iniciando a lista de alunos");

        List<AlunoDTO> alunosDTO = alunoRepository.buscarAlunos    ()
                .stream()
                .map(alunoMapper::toDTO)
                .toList();

        logger.info("Lista de alunos realizada com sucesso");
        return alunosDTO;
    }

    @Transactional
    public void inativar(Long alunoId) {
        logger.info("Iniciando o processo para inativar o aluno : {}", alunoId);

        var aluno = verificarAluno(alunoId);
        aluno.setSituacao(INATIVO);

        logger.info("Aluno inativado com sucesso.");
    }

    @Transactional
    public void ativar(Long alunoId) {
        logger.info("Iniciando o processo para ativar o aluno : {}", alunoId);

        var aluno = verificarAluno(alunoId);
        aluno.setSituacao(ATIVO);

        logger.info("Aluno ativo com sucesso.");
    }

    public AlunoDTO buscarPorId(Long alunoId) {
        logger.info("Iniciando a buscar de aluno por id : {}", alunoId);

        var aluno = verificarAluno(alunoId);

        logger.info("Busca por id feita com sucesso");

        return alunoMapper.toDTO(aluno);
    }


    private Aluno verificarAluno(Long alunoId) {
        return alunoRepository
                .buscarAlunoRepresentanteId(alunoId)
                .orElseThrow(() -> new AlunoNaoEcontradoException(
                        String.format(ALUNO_NAO_ENCONTRADO, alunoId)));
    }

}
