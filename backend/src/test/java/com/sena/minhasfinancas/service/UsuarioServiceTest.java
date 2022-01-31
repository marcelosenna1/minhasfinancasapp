package com.sena.minhasfinancas.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.repositories.UsuarioRepository;
import com.sena.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	private UsuarioService service;

	private UsuarioRepository repository;

	@Before
	public void setUp() {
		repository = Mockito.mock(UsuarioRepository.class);
		service = new UsuarioServiceImpl(repository);

	}

	@Test
	public void deveValidarEmail() throws RegraNegocioException {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		service.validarEmail("marcelomarcos2@gmail.com");
	}

	@Test
	public void deveLancarErroQuandoExistirEmail() {

		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

		try {
			service.validarEmail("email@email.com");
		} catch (RegraNegocioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
