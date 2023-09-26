package com.fiorillo.demo.orcamento.service;

import com.fiorillo.demo.orcamento.dto.OrcamentoRequest;
import com.fiorillo.demo.orcamento.dto.OrcamentoResponse;
import com.fiorillo.demo.orcamento.enums.ESituacaoOrcamento;
import com.fiorillo.demo.orcamento.model.Fornecedor;
import com.fiorillo.demo.orcamento.model.Item;
import com.fiorillo.demo.orcamento.model.Orcamento;
import com.fiorillo.demo.orcamento.repository.FornecedorRepository;
import com.fiorillo.demo.orcamento.repository.ItemRepository;
import com.fiorillo.demo.orcamento.repository.OrcamentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrcamentoService {

    private final OrcamentoRepository repository;
    private final ItemRepository itemRepository;
    private final FornecedorRepository fornecedorRepository;

    @Transactional
    public void cadastrarOrcamento(OrcamentoRequest request) {
        var fornecedores = Fornecedor.of(request.getFornecedores());
        var item = Item.of(request, fornecedores);
        var itemSalvo = itemRepository.save(item);
        fornecedores.forEach(fornecedor -> fornecedor.setItem(item));
        fornecedorRepository.saveAll(fornecedores);
        var orcamento = Orcamento.of(itemSalvo, ESituacaoOrcamento.EM_ANALISE);
        repository.save(orcamento);
    }

    @Transactional
    public List<OrcamentoResponse> listarOrcamentos() {
        var orcamentos = repository.findAll();
        return OrcamentoResponse.convertFrom(orcamentos);
    }

    public void aprovarOrcamento(Integer id) {
        var orcamento = repository.findById(id).orElseThrow(() -> new RuntimeException("Orcamento não encontrado"));

        orcamento.aprovarOrcamento();
        repository.save(orcamento);
    }

    public void reprovarOrcamento(Integer id) {
        var orcamento = repository.findById(id).orElseThrow(() -> new RuntimeException("Orcamento não encontrado"));

        orcamento.reprovarOrcamento();
        repository.save(orcamento);
    }
}
