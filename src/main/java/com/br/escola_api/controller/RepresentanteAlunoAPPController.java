package com.br.escola_api.controller;

import com.br.escola_api.dto.RepresentanteAlunoAPPDTO;
import com.br.escola_api.request.EmailRequest;
import com.br.escola_api.service.RepresentanteAlunoAPPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/aplicacao")
@CrossOrigin("*")
public class RepresentanteAlunoAPPController {

    private RepresentanteAlunoAPPService representanteAlunoAPPService;

    public RepresentanteAlunoAPPController(RepresentanteAlunoAPPService representanteAlunoAPPService) {
        this.representanteAlunoAPPService = representanteAlunoAPPService;
    }

    @PostMapping("/buscarporemail")
    public ResponseEntity<RepresentanteAlunoAPPDTO> buscarPorEmail(@RequestBody EmailRequest emailRequest) {
        RepresentanteAlunoAPPDTO representanteAlunoAPPDTO = representanteAlunoAPPService.buscarPorEmail(emailRequest);

        return ResponseEntity.ok(representanteAlunoAPPDTO);
    }

    @GetMapping("/buscarporid/{representanteId}")
    public ResponseEntity<RepresentanteAlunoAPPDTO> buscarPorId(@PathVariable(name = "representanteId")Long representanteId) {
        RepresentanteAlunoAPPDTO representanteAlunoAPPDTO = representanteAlunoAPPService.buscarPorId(representanteId);

        return ResponseEntity.ok(representanteAlunoAPPDTO);
    }

}
