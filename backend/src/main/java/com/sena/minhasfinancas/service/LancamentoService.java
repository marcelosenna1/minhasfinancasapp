package com.sena.minhasfinancas.service;

import java.util.List;

import com.sena.minhasfinancas.model.entity.Lancamento;
import com.sena.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoService {

	Lancamento salvar(Lancamento lancamento);

	Lancamento atualizar(Lancamento lancamento);

	void deletar(Lancamento lancamento);

	List<Lancamento> buscar(Lancamento lancamento);

	void atualizarStatus(Lancamento lancamento, StatusLancamento status);

}
