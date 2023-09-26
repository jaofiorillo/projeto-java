package com.fiorillo.demo.orcamento.dto;

import com.fiorillo.demo.orcamento.enums.ESituacaoOrcamento;
import com.fiorillo.demo.orcamento.model.Fornecedor;
import com.fiorillo.demo.orcamento.model.Orcamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoResponse {

    private Integer codigo;
    private ESituacaoOrcamento situacao;
    private String nomeItem;
    private String descricaoItem;
    private List<FornecedorRequest> fornecedores;

    public static OrcamentoResponse convertFrom(Orcamento orcamento) {
        var response = new OrcamentoResponse();
        response.setCodigo(orcamento.getId());
        response.setSituacao(orcamento.getSituacao());
        response.setNomeItem(orcamento.getItem() != null ? orcamento.getItem().getNome() : "");
        response.setDescricaoItem(orcamento.getItem() != null ? orcamento.getItem().getDescricao() : "");
        response.setFornecedores(orcamento.getItem() != null ? orcamento.getItem().getFornecedores() : new ArrayList<>());
        return response;
    }

    public static List<OrcamentoResponse> convertFrom(List<Orcamento> orcamentos) {
        return orcamentos.stream().map(OrcamentoResponse::convertFrom).collect(Collectors.toList());
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores.stream()
                .map(fornecedor -> {
                    var fornecedorResponse = new FornecedorRequest();
                    fornecedorResponse.setNome(fornecedor.getNome());
                    fornecedorResponse.setPreco(fornecedor.getPreco());
                    return fornecedorResponse;
                })
                .collect(Collectors.toList());
    }
}
