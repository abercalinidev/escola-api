package com.br.escola_api.controller;

import com.br.escola_api.dto.RepresentanteDTO;
import com.br.escola_api.request.RepresentanteRequest;
import com.br.escola_api.service.RepresentanteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/editar/{representanteId}")
    public ResponseEntity<RepresentanteDTO> editar(@Valid @RequestBody RepresentanteRequest representanteRequest,
                                                   @PathVariable(name = "representanteId") Long representanteId) {
        var representante = representanteService.editar(representanteRequest, representanteId);

        return ResponseEntity.status(HttpStatus.OK).body(representante);
    }

    @PutMapping("/inativar/{representanteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable(name = "representanteId") Long representanteId) {
        representanteService.inativar(representanteId);
    }

    @PutMapping("/ativar/{representanteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable(name = "representanteId") Long representanteId) {
        representanteService.ativar(representanteId);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<RepresentanteDTO>> listar() {
        var listaRepresentantes = representanteService.listar();

        return ResponseEntity.ok(listaRepresentantes);
    }

    @GetMapping("/buscar/{representanteId}")
    public ResponseEntity<RepresentanteDTO> buscarPorId(@PathVariable(name = "representanteId") Long representanteId) {
        var representante = representanteService.buscarPorId(representanteId);
        return ResponseEntity.ok(representante);
    }



}
