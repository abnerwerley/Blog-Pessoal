package br.com.blogpessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blogpessoal.models.Usuario;
import br.com.blogpessoal.repositories.UsuarioRepository;
import br.com.blogpessoal.services.UsuarioServices;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {

	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioServices services;

	// esse método usará o método findALl do repositório para fazer uma pesquisa, e
	// retornará um ResponseEntity (200) com um body de lista caso haja algum
	// usuario, se não houver devolverá um ResponseEntity(204 no content)
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
	}

	// Verificará se o o usuário com id digitado existe pelo repositório através do
	// método findById
	// Se existir, ResponseEntity(200), o usuário será mostrado, se não,
	// ResponseEntity(204)
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<Usuario> objetoOptional = repository.findById(idUsuario);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	// Utiliza a regra de nogócio para ver se o email informado já existe(já foi
	// cadastrado anteriormente), se não, ele será salvo no banco de
	// dados(cadastrado), se existir, ResponseEntity(400 - Bad Request)
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
		return services.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(400).build());
	}

	//
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario) {
		return ResponseEntity.status(201).body(repository.save(novoUsuario));
	}

	// Usará o métod findById do repositório para buscar o id no bd, se existir, vai
	// usar o método deletById do repositório para deletá-lo e retornará um
	// ResponseEntity(204 - no content), se não existir ResponseEntity(400 - bad
	// request)
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Usuario> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
		Optional<Usuario> objetoOptional = repository.findById(idUsuario);

		if (objetoOptional.isPresent()) {
			repository.deleteById(idUsuario);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
