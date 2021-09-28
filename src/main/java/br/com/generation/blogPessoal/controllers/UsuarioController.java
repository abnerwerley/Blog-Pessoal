package br.com.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blogPessoal.models.Usuario;
import br.com.generation.blogPessoal.repositories.UsuarioRepository;


@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;

	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
		// return repositorio.findAll();
	}

	@PostMapping("/salvar")
	public ResponseEntity <Usuario> salvar (@Valid @RequestBody Usuario novoUsuario){
		return ResponseEntity.status(201).body(repositorio.save(novoUsuario));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario){
		return ResponseEntity.status(201).body(repositorio.save(novoUsuario));
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Usuario> deletar (@PathVariable(value = "id_usuario")Long idUsuario){
		Optional<Usuario> objetoOptional = repositorio.findById(idUsuario);
		
		if(objetoOptional.isPresent()) {
			repositorio.deleteById(idUsuario);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
