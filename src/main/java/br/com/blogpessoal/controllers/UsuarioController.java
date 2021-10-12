package br.com.blogpessoal.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.blogpessoal.models.Usuario;
import br.com.blogpessoal.modelsDTOs.CredenciaisDTO;
import br.com.blogpessoal.modelsDTOs.UsuarioLoginDTO;
import br.com.blogpessoal.repositories.UsuarioRepository;
import br.com.blogpessoal.services.UsuarioServices;

@RestController
@RequestMapping("api/v1/usuario")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;
	private @Autowired UsuarioServices servicos;

	// Método usará o método findAll, e retornará um ResponseEntity (200) com um
	// body de lista caso haja algum
	// usuario, se não houver devolverá um ResponseEntity(204 no content)
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
	}

	// Método que procur por id, se o id existir, status 200.body; se não existir
	// status not found e mensagem para usuário

	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repositorio.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp)).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id inexistente, insira um id válido.");
		});
	}

	// Utiliza a regra de nogócio para ver se o email informado já existe(já foi
	// cadastrado anteriormente), se não, ele será salvo no banco de
	// dados(cadastrado), se existir, ResponseEntity(400 - Bad Request)

	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
		return servicos.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"E-mail inexistente,insira um e-mail válido.");
				});
	}

	@PutMapping("/credenciais")
	public ResponseEntity<CredenciaisDTO> credenciais(@Valid @RequestBody UsuarioLoginDTO usuarioParaAutenticar) {
		return servicos.pegaCredenciais(usuarioParaAutenticar);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario) {
		return servicos.atualizarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"É necessário um id de usuário para atualizar.");
				});
	}

	// Usará o métod findById do repositório para buscar o id no bd, se existir, vai
	// usar o método deletById do repositório para deletá-lo e retornará um
	// ResponseEntity(204 - no content), se não existir ResponseEntity(400 - bad
	// request)
	@DeleteMapping("/deletar/{id_usuario}")
	public ResponseEntity<Object> deletar(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repositorio.findById(idUsuario).map(resp -> {
			repositorio.deleteById(idUsuario);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"Id inexistente, insira um id válido para deletar.");
		});
	}
}
