package com.sena.minhasfinancas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.minhasfinancas.exception.RegraNegocioException;
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
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		// OBSERVAR AQUI
		Example<Lancamento> example = Example.of(lancamentoFiltro,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {

		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {

		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			new RegraNegocioException("Informe uma Descri????o v??lida");
		}
		if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			new RegraNegocioException("Informe um M??s v??lido");
		}
		if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			new RegraNegocioException("Informe um Ano v??lido");
		}
		if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			new RegraNegocioException("Informe um Usu??rio v??lida");
		}
		if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			new RegraNegocioException("Informe um Valor v??lida");
		}
		if (lancamento.getTipo() == null) {
			new RegraNegocioException("Informe um tipo de lan??amento");
		}
	}

}
