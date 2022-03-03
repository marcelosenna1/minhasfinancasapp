package com.sena.minhasfinancas.service;

import java.util.Optional;

import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	Usuario salvarUsuario(Usuario usuario);
	void validarEmail(String email) throws RegraNegocioException;
	Optional<Usuario> obterPorId(Long id);
}
