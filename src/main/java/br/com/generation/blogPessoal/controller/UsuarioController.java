package br.com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blogPessoal.model.Usuario;
import br.com.generation.blogPessoal.repository.UsuarioRepository;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;

	@GetMapping("/todos")
	public ResponseEntity <List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
		// return repositorio.findAll();
	}

}
