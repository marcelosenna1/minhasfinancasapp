package com.sena.minhasfinancas.model.repositories;

import javax.persistence.Entity;

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
@AutoConfigureTestDatabase (replace = Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void verificarExistenciaEmail() {

		// Cenário

		Usuario usuario = Usuario.builder().nome("Marcelo").email("marcelomarcos2@gmail.com").build();
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
		
		//Verificação
		Assertions.assertThat(result).isFalse();
		
	}

}
