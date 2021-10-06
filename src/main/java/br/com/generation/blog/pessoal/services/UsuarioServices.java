package br.com.generation.blog.pessoal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.generation.blog.pessoal.models.Usuario;
import br.com.generation.blog.pessoal.repositories.UsuarioRepository;


@Service
public class UsuarioServices {

	private @Autowired UsuarioRepository repository;


	/**
	 * Metodo utilizado para cadastrar usuario validando duplicidade de email no
	 * banco
	 * 
	 * @param usuarioParaCadastrar do tipo Usuario
	 * @return Optional com Usuario cadastrado caso email não seja existente
	 * @author Turma34
	 * @since 1.0
	 * 
	 */
	public Optional<Object> cadastrarUsuario(Usuario usuarioParaCadastrar) {
		Optional<Usuario> objetoOptional = repository.findByEmail(usuarioParaCadastrar.getEmail());

		if (objetoOptional.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repository.save(usuarioParaCadastrar));
		}

	}
}

