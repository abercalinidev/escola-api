package com.br.escola_api.service;

import com.br.escola_api.dto.RepresentanteDTO;
import com.br.escola_api.exception.RepresentanteNaoEncontradoException;
import com.br.escola_api.mapper.RepresentanteMapper;
import com.br.escola_api.model.Representante;
import com.br.escola_api.repository.RepresentanteRepository;
import com.br.escola_api.request.RepresentanteRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.br.escola_api.configs.ConstantesGlobais.REPRESENTANTE_NAO_ENCONTRADO;
import static com.br.escola_api.enuns.Situacao.ATIVO;
import static com.br.escola_api.enuns.Situacao.INATIVO;

@Service
@Slf4j
public class RepresentanteService {

    private RepresentanteRepository representanteRepository;

    private RepresentanteMapper representanteMapper;

    private static final Logger logger = LoggerFactory.getLogger(RepresentanteService.class);

    public RepresentanteService(RepresentanteRepository representanteRepository,
                                RepresentanteMapper representanteMapper) {
        this.representanteRepository = representanteRepository;
        this.representanteMapper = representanteMapper;
    }

    @Transactional
    public RepresentanteDTO salvar(RepresentanteRequest representanteRequest) {
        logger.info("Iniciando o processo para salvar o representante : {} ", representanteRequest);

        var representante = representanteMapper.toModel(representanteRequest);
        representante.setSituacao(ATIVO);

        representanteRepository.save(representante);

        logger.info("Representante salvo com sucesso.");

        return representanteMapper.toDTO(representante);
    }

    @Transactional
    public RepresentanteDTO editar(@Valid RepresentanteRequest representanteRequest, Long representanteId) {
        logger.info("Iniciando o processo para editar o representante : {} ", representanteRequest);

        var representante = verificarRepresentanteId(representanteId);

        representante.getEndereco().setBairro(representanteRequest.getEndereco().getBairro());
        representante.getEndereco().setCep(representanteRequest.getEndereco().getCep());
        representante.getEndereco().setCidade(representanteRequest.getEndereco().getCidade());
        representante.getEndereco().setNumero(representanteRequest.getEndereco().getNumero());
        representante.getEndereco().setEstado(representanteRequest.getEndereco().getEstado());
        representante.getEndereco().setRua(representanteRequest.getEndereco().getRua());
        representante.getEndereco().setComplemento(representanteRequest.getEndereco().getComplemento());

        BeanUtils.copyProperties(representanteRequest, representante, "id", "dataCadastro", "situacao");

        logger.info("Representante editado com sucesso.");

        return representanteMapper.toDTO(representante);
    }

    @Transactional
    public void inativar(Long representanteId) {
        logger.info("Iniciando a inativação do representante.");

        var representante = verificarRepresentanteId(representanteId);

        representante.setSituacao(INATIVO);

        logger.info("Representante inativado com sucesso.");
    }

    @Transactional
    public void ativar(Long representanteId) {
        logger.info("Iniciando a ativação do representante.");

        var representante = verificarRepresentanteId(representanteId);

        representante.setSituacao(ATIVO);

        logger.info("Representante ativado com sucesso.");
    }

    public List<RepresentanteDTO> listar() {
        logger.info("Iniciando a listagem de representantes.");

        var listaRepresentanetes = representanteRepository
                .findAll()
                .stream()
                .map(representanteMapper::toDTO)
                .sorted(Comparator.comparing(RepresentanteDTO::id))
                .toList();

        logger.info("Lista de representante buscada com sucesso.");

        return listaRepresentanetes;
    }

    public RepresentanteDTO buscarPorId(Long representanteId) {
        logger.info("Iniciando o processo para buscar representante por id : {} ", representanteId);

        var representante = representanteRepository
                    .findById(representanteId)
                    .map(representanteMapper::toDTO)
                    .orElseThrow(() -> new RepresentanteNaoEncontradoException(
                            String.format(REPRESENTANTE_NAO_ENCONTRADO, representanteId)));

        logger.info("Busca do representante feita com sucesso.");

        return representante;
    }

    private Representante verificarRepresentanteId(Long representanteId) {
        return representanteRepository
                .findById(representanteId)
                .orElseThrow(() -> new RepresentanteNaoEncontradoException(
                        String.format(REPRESENTANTE_NAO_ENCONTRADO, representanteId)));
    }

}
