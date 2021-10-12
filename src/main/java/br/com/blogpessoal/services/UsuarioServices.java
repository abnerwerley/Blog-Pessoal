package br.com.blogpessoal.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.blogpessoal.models.Usuario;
import br.com.blogpessoal.modelsDTOs.CredenciaisDTO;
import br.com.blogpessoal.modelsDTOs.UsuarioLoginDTO;
import br.com.blogpessoal.repositories.UsuarioRepository;

@Service
public class UsuarioServices {

	private @Autowired UsuarioRepository repositorio;

	/**
	 * Método estatico que recebe a senha do usuario o criptografa
	 * 
	 * @param senha
	 * @return String da senha criptografada
	 * @since 1.0
	 * @author Abner Werley Silva
	 * 
	 */

	private static String encriptador(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	/**
	 * Metodo utilizado para cadastrar usuario validando duplicidade de email no
	 * banco
	 * 
	 * @param usuarioParaCadastrar do tipo Usuario
	 * @return Optional com Usuario cadastrado caso email não seja existente
	 * @author Abner Werley Silva
	 * @since 1.0
	 * 
	 */
	public Optional<Object> cadastrarUsuario(Usuario usuarioParaCadastrar) {
		return repositorio.findByEmail(usuarioParaCadastrar.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			usuarioParaCadastrar.setSenha(encriptador(usuarioParaCadastrar.getSenha()));
			return Optional.ofNullable(repositorio.save(usuarioParaCadastrar));
		});

	}

	/**
	 * Metodo utilizado para atualizar usuario no banco de dados
	 * 
	 * @param usuarioParaAtualizar do tipo Usuario
	 * @return Optional com Usuario atualizado
	 * @author Abner Werley Silva
	 * @since 1.5
	 * 
	 */

	public Optional<Usuario> atualizarUsuario(Usuario usuarioParaAtualizar) {
		return repositorio.findById(usuarioParaAtualizar.getId_usuario()).map(resp -> {
			resp.setNome(usuarioParaAtualizar.getNome());
			resp.setSenha(encriptador(usuarioParaAtualizar.getSenha()));
			return Optional.ofNullable(repositorio.save(resp));

		}).orElseGet(() -> {
			return Optional.empty();
		});
	}

	/**
	 * Metodo estatico utilizado para gerar token formato Basic
	 * 
	 * <p>
	 * estrutura ex. gustavo@email.com:134652
	 * <p>
	 * estruturaBase64 ex. cGFtZWxhQGVtYWlsLmNvbToxMzQ2NTI
	 * <p>
	 * estruturaBasic = Basic cGFtZWxhQGVtYWlsLmNvbToxMzQ2NTI
	 * 
	 * @param email
	 * @param senha
	 * @return Token no formato Basic para autenticação
	 * @since 1.0
	 * @author Abner Werley Silva
	 * 
	 */

	private static String gerarToken(String email, String senha) {
		String estrutura = email + ":" + senha;
		byte[] estruturaBase64 = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(estruturaBase64);
	}

	/**
	 * Metodo utilizado para pegar credenciais do usuario com Tokem (Formato Basic),
	 * este método sera utilizado para retornar ao front o token utilizado para ter
	 * acesso aos dados do usuario e mantelo logado no sistema
	 * 
	 * @param usuarioParaAutenticar do tipo UsuarioLoginDTO necessario email e senha
	 *                              para validar
	 * @return ResponseEntity com CredenciaisDTO preenchido com informações mais o
	 *         Token
	 * @since 1.0
	 * @author Abner Werley Silva
	 * 
	 */

	public ResponseEntity<CredenciaisDTO> pegaCredenciais(UsuarioLoginDTO usuarioParaAutenticar) {
		return repositorio.findByEmail(usuarioParaAutenticar.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), resp.getSenha())) {

				CredenciaisDTO objetoCredenciaisDTO = new CredenciaisDTO();

				objetoCredenciaisDTO
						.setToken(gerarToken(usuarioParaAutenticar.getEmail(), usuarioParaAutenticar.getSenha()));
				objetoCredenciaisDTO.setIdUsuario(resp.getId_usuario());
				objetoCredenciaisDTO.setNome(resp.getNome());
				objetoCredenciaisDTO.setEmail(resp.getEmail());
				objetoCredenciaisDTO.setSenha(resp.getSenha());

				return ResponseEntity.status(201).body(objetoCredenciaisDTO);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Incorreta!");
			}
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!");
		});

	}

}
