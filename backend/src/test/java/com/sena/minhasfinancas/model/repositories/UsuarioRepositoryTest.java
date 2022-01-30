package com.sena.minhasfinancas.model.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sena.minhasfinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void verificarExistenciaEmail() {

		// Cenário

		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		// Ação/execução
		boolean result = repository.existsByEmail("marcelomarcos2@gmail.com");

		// verificado
		Assertions.assertThat(result).isTrue();

	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {

		// Ação
		boolean result = repository.existsByEmail("marcelomarcos2@gmail.com");

		// Verificação
		Assertions.assertThat(result).isFalse();

	}

	@Test
	public void devePersistirUmUsuarioNaBaseDeDados() {
		// Cenário
		Usuario usuario = criarUsuario();

		// Ação

		Usuario usuarioSalvo = repository.save(usuario);

		// Verificação
		Assertions.assertThat(usuario.getId()).isNotNull();
	}

	@Test
	public void deveBuscarUmUsuarioPorEmail() {

		// Cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// Verificação
		Optional<Usuario> result = repository.findByEmail("marcelomarcos2@gmail.com");
		Assertions.assertThat(result.isPresent()).isTrue();
	}

	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {

		// Cenário
		
		// Verificação
		Optional<Usuario> result = repository.findByEmail("marcelomarcos2@gmail.com");
		Assertions.assertThat(result.isPresent()).isFalse();
	}

	public static Usuario criarUsuario() {

		return Usuario.builder().email("marcelomarcos2@gmail.com").nome("ma").senha("1234").build();
	}

}
