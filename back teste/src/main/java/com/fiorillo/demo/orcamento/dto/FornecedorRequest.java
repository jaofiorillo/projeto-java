package com.fiorillo.demo.orcamento.dto;

import com.fiorillo.demo.orcamento.model.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FornecedorRequest {

    private String nome;
    private Float preco;
}
