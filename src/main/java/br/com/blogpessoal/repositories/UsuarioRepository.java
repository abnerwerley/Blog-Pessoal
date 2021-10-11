package br.com.blogpessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blogpessoal.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);// consulta pelo títulos de postagens

	/**
	 * Método utilizado para pesquisa pela coluna email da tb_usuario
	 * 
	 * @param email
	 * 
	 * @return Optional com usuário
	 * 
	 * @author Abner Werley Silva
	 * 
	 * @since 1.0
	 * 
	 */
	public Optional<Usuario> findByEmail(String email); // faz pesquisa de usuários por email

	/**
	 * Método utilizado para pesquisar pela coluna nome da tb_usuario
	 * 
	 * @param nome
	 * 
	 * @return List com usuário
	 * 
	 * @author Abner Werley Silva
	 * 
	 * @since 1.0
	 * 
	 */
	public List<Usuario> findByNomeContainingIgnoreCase(String nome);// pesquisa por parte do nome que já acha

}
