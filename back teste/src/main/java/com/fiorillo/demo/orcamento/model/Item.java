package com.fiorillo.demo.orcamento.model;

import com.fiorillo.demo.orcamento.dto.FornecedorRequest;
import com.fiorillo.demo.orcamento.dto.OrcamentoRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Builder
@Table(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @SequenceGenerator(name = "SEQ_ITEM", sequenceName = "SEQ_ITEM", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ITEM", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Size(max = 80)
    @Column(name = "DESCRICAO", nullable = false, length = 80)
    private String descricao;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Fornecedor> fornecedores;

    public static Item of(OrcamentoRequest request, List<Fornecedor> fornecedores) {
        var item = new Item();
        item.setNome(request.getNomeItem());
        item.setDescricao(request.getDescricao());
        item.setFornecedores(fornecedores);

        return item;
    }
}
