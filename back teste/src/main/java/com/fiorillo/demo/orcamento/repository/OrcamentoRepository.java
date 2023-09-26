package com.fiorillo.demo.orcamento.repository;

import com.fiorillo.demo.orcamento.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrcamentoRepository extends JpaRepository<Orcamento, Integer> {

    Optional<Orcamento> findById(Integer id);

    List<Orcamento> findAll();
}
