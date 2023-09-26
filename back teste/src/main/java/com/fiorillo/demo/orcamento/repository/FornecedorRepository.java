package com.fiorillo.demo.orcamento.repository;

import com.fiorillo.demo.orcamento.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}
