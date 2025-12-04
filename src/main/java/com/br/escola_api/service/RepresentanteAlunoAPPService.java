package com.br.escola_api.service;

import com.br.escola_api.dto.RepresentanteAlunoAPPDTO;
import com.br.escola_api.exception.RepresentanteEmailNaoEncontrado;
import com.br.escola_api.exception.RepresentanteNaoEncontradoException;
import com.br.escola_api.mapper.RepresentanteAlunoAPPMApper;
import com.br.escola_api.repository.RepresentanteRepository;
import com.br.escola_api.request.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.br.escola_api.configs.ConstantesGlobais.BUSCAR_REPRESENTANTE_EMAIL;
import static com.br.escola_api.configs.ConstantesGlobais.REPRESENTANTE_NAO_ENCONTRADO;
import static java.lang.String.format;

@Service
@Slf4j
public class RepresentanteAlunoAPPService {

    private RepresentanteAlunoAPPMApper representanteAlunoAPPMApper;

    private RepresentanteRepository representanteRepository;

    private static final Logger logger = LoggerFactory.getLogger(RepresentanteAlunoAPPService.class);


    public RepresentanteAlunoAPPService(RepresentanteAlunoAPPMApper representanteAlunoAPPMApper,
                                        RepresentanteRepository representanteRepository) {
        this.representanteAlunoAPPMApper = representanteAlunoAPPMApper;
        this.representanteRepository = representanteRepository;
    }

    public RepresentanteAlunoAPPDTO buscarPorEmail(EmailRequest emailRequest) {
        logger.info("Iniciando o processo para buscar representante por email : {} ", emailRequest);

        var representante = representanteRepository
                .findByEmail(emailRequest.getEmail())
                .map(representanteAlunoAPPMApper::toDTO)
                .orElseThrow(() -> new RepresentanteEmailNaoEncontrado(
                        format(BUSCAR_REPRESENTANTE_EMAIL, emailRequest.getEmail())));

        logger.info("Busca do representante feita com sucesso.");

        return representante;
    }

    public RepresentanteAlunoAPPDTO buscarPorId(Long representanteId) {
        logger.info("Iniciando o processo para buscar representante por id : {} ", representanteId);

        RepresentanteAlunoAPPDTO representanteAlunoAPPDTO = representanteRepository
                .verificarId(representanteId)
                .map(representanteAlunoAPPMApper::toDTO)
                .orElseThrow(() -> new RepresentanteNaoEncontradoException(
                        format(REPRESENTANTE_NAO_ENCONTRADO, representanteId)));

        logger.info("Busca feita com sucesso por representante id");

        return representanteAlunoAPPDTO;
    }
}
