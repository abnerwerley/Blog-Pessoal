package br.com.generation.blogPessoal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import java.util.List;

import br.com.generation.blogPessoal.models.Postagem;
import br.com.generation.blogPessoal.repositories.PostagemRepository;

@Controller
@RequestMapping("/postagens")
@CrossOrigin("*") // para ser consumido de qualquer origem
public class PostagemController {

	@Autowired // garantimos que todos os serviços da interface seja acessada a partir do
				// controller
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() { // retorna uma lista, e essa será do tipo postagens
		return ResponseEntity.ok(repository.findAll());
	}

}
