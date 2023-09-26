package com.fiorillo.demo.orcamento.controller;

import com.fiorillo.demo.orcamento.dto.OrcamentoRequest;
import com.fiorillo.demo.orcamento.dto.OrcamentoResponse;
import com.fiorillo.demo.orcamento.service.OrcamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/orcamento")
public class OrcamentoController {

    private final OrcamentoService service;

    @PostMapping("cadastrar")
    private void cadastrarOrcamento(@RequestBody @Valid OrcamentoRequest request) {
        service.cadastrarOrcamento(request);
    }

    @GetMapping("listar")
    private List<OrcamentoResponse> getAll() {
        return service.listarOrcamentos();
    }

    @PostMapping("gerencia/aprovar/{id}")
    private void aprovarOrcamento(@PathVariable Integer id) {
        service.aprovarOrcamento(id);
    }

    @PostMapping("gerencia/reprovar/{id}")
    private void reprovarOrcamento(@PathVariable Integer id) {
        service.reprovarOrcamento(id);
    }
}
