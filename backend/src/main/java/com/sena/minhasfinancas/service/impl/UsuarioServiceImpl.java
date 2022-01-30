package com.sena.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sena.minhasfinancas.exception.ErroAutenticacao;
import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.entity.Usuario;
import com.sena.minhasfinancas.model.repositories.UsuarioRepository;
import com.sena.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado");
		}
		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		try {
			validarEmail(usuario.getEmail());
		} catch (RegraNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) throws RegraNegocioException {

		boolean existe = repository.existsByEmail(email);

		if (existe) {
			throw new RegraNegocioException("Já existe um usuário com esse email");
		}

	}

}
