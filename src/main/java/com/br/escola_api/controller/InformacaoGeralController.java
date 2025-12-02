package com.br.escola_api.controller;

import com.br.escola_api.dto.InformacaoGeralDTO;
import com.br.escola_api.request.InformacaoGeralRequest;
import com.br.escola_api.service.InformacaoGeralService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/informacaogeral")
public class InformacaoGeralController {

    private InformacaoGeralService informacaoGeralService;

    public InformacaoGeralController(InformacaoGeralService informacaoGeralService) {
        this.informacaoGeralService = informacaoGeralService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<InformacaoGeralDTO> salvar(@Valid @RequestBody InformacaoGeralRequest informacaoGeralRequest) {
        InformacaoGeralDTO informacaoGeralDTO = informacaoGeralService.salvar(informacaoGeralRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(informacaoGeralDTO);
    }

    @PutMapping("/editar/{informacaoGeralId}")
    public ResponseEntity<InformacaoGeralDTO> editar(@Valid @RequestBody InformacaoGeralRequest informacaoGeralRequest,
                                                     @PathVariable(name = "informacaoGeralId") Long informacaoGeralId) {
        InformacaoGeralDTO informacaoGeralDTO = informacaoGeralService.editar(informacaoGeralRequest, informacaoGeralId);

        return ResponseEntity.ok(informacaoGeralDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<InformacaoGeralDTO>> listar() {
        List<InformacaoGeralDTO> listaInformacoesGeraisDTO = informacaoGeralService.listar();

        return ResponseEntity.ok(listaInformacoesGeraisDTO);
    }

    @GetMapping("/buscar/{informacaoGeralId}")
    public ResponseEntity<InformacaoGeralDTO> buscarPorId(@PathVariable(name = "informacaoGeralId")
                                                              Long informacaoGeralId) {
        InformacaoGeralDTO informacaoGeralDTO = informacaoGeralService.buscarPorId(informacaoGeralId);

        return ResponseEntity.ok(informacaoGeralDTO);
    }

    @DeleteMapping("/excluir/{informacaoGeralId}")
    @ResponseStatus(NO_CONTENT)
    public void excluir(@PathVariable(name = "informacaoGeralId") Long informacaoGeralId) {
        informacaoGeralService.excluir(informacaoGeralId);
    }

}
