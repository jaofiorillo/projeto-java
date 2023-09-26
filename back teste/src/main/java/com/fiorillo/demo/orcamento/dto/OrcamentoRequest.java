package com.fiorillo.demo.orcamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class OrcamentoRequest {

    @NotBlank
    private String nomeItem;
    private String descricao;
    @NotNull
    private List<FornecedorRequest> fornecedores;
}
