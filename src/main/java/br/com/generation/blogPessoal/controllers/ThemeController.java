package br.com.generation.blogPessoal.controllers;

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

import br.com.generation.blogPessoal.models.Theme;
import br.com.generation.blogPessoal.repositories.ThemeRepository;

@RestController
@RequestMapping("api/v1/theme")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ThemeController {

	private @Autowired ThemeRepository repository;

	@GetMapping("/todos")
	public ResponseEntity<List<Theme>> ListAll() {
		List<Theme> objetoLista = repository.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
	}

	@GetMapping("/{id_tema}")
	public ResponseEntity<Theme> pegarPorId(@PathVariable(value = "id_tema") Long idTema) {
		Optional<Theme> objetoOptional = repository.findById(idTema);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<Theme> save(@Valid @RequestBody Theme newTheme) {
		return ResponseEntity.status(201).body(repository.save(newTheme));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Theme> update(@Valid @RequestBody Theme newTheme) {
		return ResponseEntity.status(201).body(repository.save(newTheme));
	}

	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Theme> delete(@PathVariable(value = "id_usuario") Long idTheme) {
		Optional<Theme> objetoOptional = repository.findById(idTheme);

		if (objetoOptional.isPresent()) {
			repository.deleteById(idTheme);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
