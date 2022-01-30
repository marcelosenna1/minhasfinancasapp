package com.sena.minhasfinancas.service;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.entity.Usuario;
import com.sena.minhasfinancas.model.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	private UsuarioService service;

	@Autowired
	private UsuarioRepository repository;

	@Test
	public void deveValidarEmail() throws RegraNegocioException {

		repository.deleteAll();

		service.validarEmail("marcelomarcos2@gmail.com");
	}

	@Test
	public void deveLancarErroQuandoExistirEmail() {

		Usuario usuario = Usuario.builder().nome("marcos").email("marcos@marcos.com").build();
		repository.save(usuario);
		
		try {
			service.validarEmail("email@email.com");
		} catch (RegraNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
