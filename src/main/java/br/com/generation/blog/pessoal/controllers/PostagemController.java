package br.com.generation.blog.pessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.generation.blog.pessoal.models.Postagem;
import br.com.generation.blog.pessoal.repositories.PostagemRepository;

@Controller
@RequestMapping("/api/v1/postagem")
@CrossOrigin("*") // para ser consumido de qualquer origem
public class PostagemController {

	@Autowired // garantimos que todos os serviços da interface seja acessada a partir do
				// controller
	private PostagemRepository repository;

	@GetMapping("/todas")
	public ResponseEntity<List<Postagem>> pegarTodas() {
		List<Postagem> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> GetById(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoOptional = repository.findById(idPostagem);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvar(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Postagem> atualizar(@Valid @RequestBody Postagem NovaPostagem) {
		return ResponseEntity.status(201).body(repository.save(NovaPostagem));
	}

	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<Postagem> deletar(@PathVariable(value = "id_postagem") Long IdPostagem) {
		Optional<Postagem> objetoOptional = repository.findById(IdPostagem);

		if (objetoOptional.isPresent()) {
			repository.deleteById(IdPostagem);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
