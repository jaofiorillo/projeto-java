package com.fiorillo.demo.orcamento.repository;

import com.fiorillo.demo.orcamento.model.Fornecedor;
import com.fiorillo.demo.orcamento.model.Item;
import com.fiorillo.demo.orcamento.model.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAll();

}
