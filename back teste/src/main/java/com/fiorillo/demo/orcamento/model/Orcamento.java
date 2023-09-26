package com.fiorillo.demo.orcamento.model;

import com.fiorillo.demo.orcamento.dto.OrcamentoRequest;
import com.fiorillo.demo.orcamento.enums.ESituacaoOrcamento;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@QueryEntity
@Table(name = "ORCAMENTO")
@AllArgsConstructor
@NoArgsConstructor
public class Orcamento {

    @Id
    @SequenceGenerator(name = "SEQ_ORCAMENTO", sequenceName = "SEQ_ORCAMENTO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ORCAMENTO", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name = "FK_ITEM", foreignKey = @ForeignKey(name = "FK_ORCAMENTO_ITEM"), referencedColumnName = "ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO", length = 100, nullable = false)
    private ESituacaoOrcamento situacao;

    public static Orcamento of(Item item, ESituacaoOrcamento situacao) {
        return Orcamento.builder().item(item).situacao(situacao).build();
    }

    public void aprovarOrcamento() {
        this.situacao = ESituacaoOrcamento.APROVADO;
    }

    public void reprovarOrcamento() {
        this.situacao = ESituacaoOrcamento.REPROVADO;
    }
}
