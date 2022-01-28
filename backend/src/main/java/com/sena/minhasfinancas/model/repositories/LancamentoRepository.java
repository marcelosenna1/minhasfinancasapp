package com.sena.minhasfinancas.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
