package com.br.escola_api.controller;

import com.br.escola_api.dto.RepresentanteDTO;
import com.br.escola_api.request.RepresentanteRequest;
import com.br.escola_api.service.RepresentanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/representante")
@CrossOrigin("*")
public class RepresentanteController {

    private RepresentanteService representanteService;

    public RepresentanteController(RepresentanteService representanteService) {
        this.representanteService = representanteService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<RepresentanteDTO> salvar(@Valid @RequestBody RepresentanteRequest representanteRequest) {
        var representante = representanteService.salvar(representanteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(representante);
    }

}
