package com.sena.minhasfinancas.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.minhasfinancas.api.dto.LancamentoDTO;
import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.entity.Lancamento;
import com.sena.minhasfinancas.model.entity.Usuario;
import com.sena.minhasfinancas.model.enums.StatusLancamento;
import com.sena.minhasfinancas.model.enums.TipoLancamento;
import com.sena.minhasfinancas.service.LancamentoService;
import com.sena.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService service;

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<Lancamento> salvar(@RequestBody LancamentoDTO dto) {

		return null;

	}

	@SuppressWarnings("unused")
	private Lancamento converter(LancamentoDTO dto) throws RegraNegocioException {

		Lancamento lancamento = new Lancamento();
		lancamento.setDescricao(dto.getDescricao());
		lancamento.setAno(dto.getAno());
		lancamento.setMes(dto.getMes());
		lancamento.setValor(dto.getValor());

		Usuario usuario = usuarioService.obterPorId(dto.getIdUsuario())
				.orElseThrow(() -> new RegraNegocioException("Usuário não encontrado para Id informado"));

		lancamento.setUsuario(usuario);
		lancamento.setTipo(TipoLancamento.valueOf(dto.getTipo()));
		lancamento.setStatus(StatusLancamento.valueOf(dto.getStatus()));
		return lancamento;

	}
}
