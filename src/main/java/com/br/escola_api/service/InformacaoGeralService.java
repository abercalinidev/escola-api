package com.br.escola_api.service;

import com.br.escola_api.dto.InformacaoGeralDTO;
import com.br.escola_api.exception.AlunoNaoEcontradoException;
import com.br.escola_api.exception.InformacaoGeralNaoEncontradoExcpetion;
import com.br.escola_api.mapper.InformacaoGeralMapper;
import com.br.escola_api.model.Aluno;
import com.br.escola_api.model.InformacaoGeral;
import com.br.escola_api.repository.AlunoRepository;
import com.br.escola_api.repository.InformacaoGeralRepository;
import com.br.escola_api.request.InformacaoGeralRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.br.escola_api.configs.ConstantesGlobais.ALUNO_NAO_ENCONTRADO;
import static com.br.escola_api.configs.ConstantesGlobais.INFORMACAO_GERAL_NAO_ENCONTRADA;
import static java.lang.String.format;

@Service
@Slf4j
public class InformacaoGeralService {

    private InformacaoGeralMapper informacaoGeralMapper;

    private InformacaoGeralRepository informacaoGeralRepository;
    private AlunoRepository alunoRepository;

    private static final Logger logger = LoggerFactory.getLogger(InformacaoGeralService.class);

    public InformacaoGeralService(InformacaoGeralMapper informacaoGeralMapper,
                                  InformacaoGeralRepository informacaoGeralRepository,
                                  AlunoRepository alunoRepository){
        this.informacaoGeralMapper = informacaoGeralMapper;
        this.informacaoGeralRepository = informacaoGeralRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public InformacaoGeralDTO salvar(InformacaoGeralRequest informacaoGeralRequest) {
        logger.info("Iniciando o processo para salvar a informação geral {}", informacaoGeralRequest);

        Aluno aluno = verificarAluno(informacaoGeralRequest.getAluno().getId());

        InformacaoGeral informacaoGeral = informacaoGeralMapper.toModel(informacaoGeralRequest);
        informacaoGeral.setAluno(aluno);

        informacaoGeralRepository.save(informacaoGeral);

        logger.info("Informação geral salva com sucesso.");

        return informacaoGeralMapper.toDTO(informacaoGeral);
    }

    @Transactional
    public InformacaoGeralDTO editar(@Valid InformacaoGeralRequest informacaoGeralRequest, Long informacaoGeralId) {
        logger.info("Iniciando o processo de editar Informação geral : {}", informacaoGeralRequest);

        Aluno aluno = verificarAluno(informacaoGeralRequest.getAluno().getId());
        InformacaoGeral informacaoGeral = verificarInformacaoId(informacaoGeralId);

        BeanUtils.copyProperties(informacaoGeralRequest, informacaoGeral, "id");
        informacaoGeral.setAluno(aluno);

        logger.info("Informação geral editada com sucesso");

        return informacaoGeralMapper.toDTO(informacaoGeral);
    }

    public List<InformacaoGeralDTO> listar() {
        logger.info("Iniciando o processo para listar informações gerais");

        List<InformacaoGeralDTO> listaInformacoesGerais = informacaoGeralRepository
                .findAll()
                .stream()
                .map(informacaoGeralMapper::toDTO)
                .toList();

        logger.info("Listagem das informações gerais concluida com sucesso");

        return listaInformacoesGerais;
    }

    @Transactional
    public void excluir(Long informacaoGeralId) {
        logger.info("Iniciando o processo para excluir uma informação geral : {}", informacaoGeralId);

        InformacaoGeral informacaoGeral = verificarInformacaoId(informacaoGeralId);

        informacaoGeralRepository.delete(informacaoGeral);

        logger.info("Informação geral excluida com sucesso");
    }

    public InformacaoGeralDTO buscarPorId(Long informacaoGeralId) {
        logger.info("Iniciando o processo para buscar informacao geral id : {}", informacaoGeralId);

        InformacaoGeralDTO informacaoGeralDTO = informacaoGeralRepository
                .findById(informacaoGeralId)
                .map(informacaoGeralMapper::toDTO)
                .orElseThrow(() -> new InformacaoGeralNaoEncontradoExcpetion(
                        format(INFORMACAO_GERAL_NAO_ENCONTRADA, informacaoGeralId)));

        logger.info("Busca por id da informação gereal com sucesso");


        return informacaoGeralDTO;
    }

    private InformacaoGeral verificarInformacaoId(Long informacaoGeralId) {
        return informacaoGeralRepository
                .findById(informacaoGeralId)
                .orElseThrow(() -> new InformacaoGeralNaoEncontradoExcpetion(
                        format(INFORMACAO_GERAL_NAO_ENCONTRADA, informacaoGeralId)));
    }

    private Aluno verificarAluno(Long alunoId) {
        return alunoRepository
                .findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEcontradoException(
                        format(ALUNO_NAO_ENCONTRADO, alunoId)));
    }

}
