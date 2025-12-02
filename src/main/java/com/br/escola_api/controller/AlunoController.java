package com.br.escola_api.controller;

import com.br.escola_api.dto.AlunoDTO;
import com.br.escola_api.request.AlunoRequest;
import com.br.escola_api.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/alunos")
@CrossOrigin("*")
public class AlunoController {

    private AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<AlunoDTO> salvar(@Valid @RequestBody AlunoRequest alunoRequest) {
        var aluno = alunoService.salvar(alunoRequest);

        return ResponseEntity.status(CREATED).body(aluno);
    }

    @PutMapping("/editar/{alunoId}")
    public ResponseEntity<AlunoDTO> editar(@Valid @RequestBody AlunoRequest alunoRequest,
                                           @PathVariable(name = "alunoId") Long alunoId) {
        var aluno = alunoService.editar(alunoRequest, alunoId);

        return ResponseEntity.status(OK).body(aluno);
    }

    @GetMapping("buscar/{alunoId}")
    public ResponseEntity<AlunoDTO> buscarPorId(@PathVariable(name = "alunoId") Long alunoId) {
        var aluno = alunoService.buscarPorId(alunoId);

        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/inativar/{alunoId}")
    public ResponseEntity<AlunoDTO> inativar(@PathVariable(name = "alunoId") Long alunoId) {
        alunoService.inativar(alunoId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativar/{alunoId}")
    public ResponseEntity<AlunoDTO> ativar(@PathVariable(name = "alunoId") Long alunoId) {
        alunoService.ativar(alunoId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlunoDTO>> listar() {
        var alunos = alunoService.listarAlunos();

        return ResponseEntity.ok(alunos);
    }

}
