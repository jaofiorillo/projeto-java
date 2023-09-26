package com.fiorillo.demo.orcamento.model;

import com.fiorillo.demo.orcamento.dto.FornecedorRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@Table(name = "FORNECEDOR")
@AllArgsConstructor
@NoArgsConstructor
public class Fornecedor {

    @Id
    @SequenceGenerator(name = "SEQ_FORNECEDOR", sequenceName = "SEQ_FORNECEDOR", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_FORNECEDOR", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "PRECO", nullable = false)
    private Float preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_ITEM", foreignKey = @ForeignKey(name = "FK_FORNECEDOR_ITEM"))
    private Item item;

    public static List<Fornecedor> of(List<FornecedorRequest> request) {
        return request.stream()
                .map(Fornecedor::of)
                .collect(Collectors.toList());
    }

    private static Fornecedor of (FornecedorRequest request) {
        return Fornecedor.builder()
                .nome(request.getNome())
                .preco(request.getPreco())
                .build();
    }

    public Fornecedor(FornecedorRequest fornecedorRequest) {
        this.nome = fornecedorRequest.getNome();
        this.preco = fornecedorRequest.getPreco();
    }
}
