package com.sena.minhasfinancas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.minhasfinancas.model.entity.Lancamento;
import com.sena.minhasfinancas.model.enums.StatusLancamento;
import com.sena.minhasfinancas.model.repositories.LancamentoRepository;
import com.sena.minhasfinancas.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	@Autowired
	private LancamentoRepository repository;

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		return repository.save(lancamento);
	}

	@Override
	public Lancamento atualizar(Lancamento lancamento) {
		return null;
	}

	@Override
	public void deletar(Lancamento lancamento) {

	}

	@Override
	public List<Lancamento> buscar(Lancamento lancamento) {
		return null;
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {

	}

}
