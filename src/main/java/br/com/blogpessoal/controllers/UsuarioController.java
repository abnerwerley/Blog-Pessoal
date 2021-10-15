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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {

	private @Autowired UsuarioRepository repositorio;
	private @Autowired UsuarioServices servicos;

	@ApiOperation(value = "Retorna Lista com usuários no sistema")
	@ApiResponses(value = {
		 @ApiResponse(code = 200, message = "Retorna Lista de usuários."),
		 @ApiResponse(code = 204, message = "Retorna sem usuários.")
			
	})
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> pegarTodos() {
		List<Usuario> objetoLista = repositorio.findAll();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();// o build vai montar toda a resposta(combo) do status 204
		} else {
			return ResponseEntity.status(200).body(objetoLista); //
		}
	}

	@ApiOperation(value = "Busca usuario por nome")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna usuario existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente")
	})
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable(value = "nome_usuario") String nome) {
		List<Usuario> objetoLista = repositorio.findAllByNomeContainingIgnoreCase(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@ApiOperation(value = "Busca usuário por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "retorna usuário existente"),
			@ApiResponse(code = 400, message = "retorno inexistente") })
	@GetMapping("/{id_usuario}")
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id_usuario") Long idUsuario) {
		return repositorio.findById(idUsuario).map(resp -> ResponseEntity.status(200).body(resp)).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id inexistente, insira um id válido.");
		});
	}

	@ApiOperation(value = "cadastra novo usuário no sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Usuário cadastrado."),
			@ApiResponse(code = 400, message = "Erro de requisição.")
	})
	@PostMapping("/salvar")
	public ResponseEntity<Object> salvar(@Valid @RequestBody Usuario novoUsuario) {
		return servicos.cadastrarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"E-mail inexistente,insira um e-mail válido.");
				});
	}

	@ApiOperation(value = "Autentica usuário no sistema.")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Retorna credenciais do usuário"),
			@ApiResponse(code = 400, message = "Erro de requisição.")
	})
	
	@PutMapping("/credenciais")
	public ResponseEntity<CredenciaisDTO> credenciais(@Valid @RequestBody UsuarioLoginDTO usuarioParaAutenticar) {
		return servicos.pegaCredenciais(usuarioParaAutenticar);
	}

	@ApiOperation(value = "Atualiza dados de um usuário existente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Usuário atualizado"),
			@ApiResponse(code = 400, message = "Erro de requisição.")
	})
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> atualizar(@Valid @RequestBody Usuario novoUsuario) {
		return servicos.atualizarUsuario(novoUsuario).map(resp -> ResponseEntity.status(201).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
							"É necessário um id de usuário para atualizar.");
				});
	}

	@ApiOperation(value = "Deleta um usuário existente.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuário deletado."),
			@ApiResponse(code = 401, message = "Id inválido.")
	})
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
