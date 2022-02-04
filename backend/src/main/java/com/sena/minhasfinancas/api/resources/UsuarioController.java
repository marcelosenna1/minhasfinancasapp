package com.sena.minhasfinancas.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.minhasfinancas.api.dto.UsuarioDTO;
import com.sena.minhasfinancas.exception.ErroAutenticacao;
import com.sena.minhasfinancas.model.entity.Usuario;
import com.sena.minhasfinancas.service.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO dto) {

		Usuario usuario = Usuario.builder().nome(dto.getNome()).email(dto.getEmail()).senha(dto.getSenha()).build();
		Usuario usuarioSalvo = service.salvarUsuario(usuario);
		ResponseEntity<Usuario> responseEntity = new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
		return responseEntity;

	}

	@PostMapping("/autenticar")
	public ResponseEntity<Object> autenticar(@RequestBody UsuarioDTO dto) {

		try {
			Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}


	}

}
