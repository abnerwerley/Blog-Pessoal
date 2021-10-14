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

import br.com.blogpessoal.models.Tema;
import br.com.blogpessoal.repositories.TemaRepository;

@RestController
@RequestMapping("api/v1/tema")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class TemaController {

	private @Autowired TemaRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<Tema>> ListarTodos() {
		List<Tema> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
	}

	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> pegarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Tema> objetoOptional = repository.findById(idTema);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Tema> salvar(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repository.save(novoTema));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Tema> atualizar(@Valid @RequestBody Tema novoTema) {
		return ResponseEntity.status(201).body(repository.save(novoTema));
	}

	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Tema> deletar(@PathVariable(value = "id_usuario") Long idTema) {
		Optional<Tema> objetoOptional = repository.findById(idTema);

		if (objetoOptional.isPresent()) {
			repository.deleteById(idTema);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
