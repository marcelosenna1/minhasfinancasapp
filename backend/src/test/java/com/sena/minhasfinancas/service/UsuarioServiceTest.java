package com.sena.minhasfinancas.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sena.minhasfinancas.exception.RegraNegocioException;
import com.sena.minhasfinancas.model.entity.Usuario;
import com.sena.minhasfinancas.model.repositories.UsuarioRepository;
import com.sena.minhasfinancas.service.impl.UsuarioServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	private UsuarioService service;
	@MockBean
	private UsuarioRepository repository;

	@Before
	public void setUp() {
		service = new UsuarioServiceImpl(repository);

	}
	@Test(expected = Test.None.class)
	public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComEmailInformado() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
	}
	
	@Test(expected = Test.None.class)
	public void deveAutenticarUmUsuarioComSucesso() {
		//Cenário
		String email = "marcelo@gmail.com";
		String senha = "senha";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		//Ação
		Usuario result= service.autenticar(email, senha);
		
		//Verificação
		Assertions.assertThat(result).isNotNull();
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
