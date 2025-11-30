package com.br.escola_api.service;

import com.br.escola_api.dto.RepresentanteDTO;
import com.br.escola_api.mapper.RepresentanteMapper;
import com.br.escola_api.repository.RepresentanteRepository;
import com.br.escola_api.request.RepresentanteRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

        representanteRepository.save(representante);

        logger.info("Representante salvo com sucesso.");

        return representanteMapper.toDTO(representante);
    }

}
