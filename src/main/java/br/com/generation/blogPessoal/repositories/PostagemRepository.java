package br.com.generation.blogPessoal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.blogPessoal.models.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	/*
	 * Método utilizado para pesquisa pela coluna titulo da tb_postagem
	 * 
	 * @param titulo
	 * 
	 * @return List com titulo
	 * 
	 * @author Abner Werley Silva
	 * 
	 * @since 1.0
	 * 
	 */
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);// consulta pelo títulos de postagens
																				// //consulta pelo títulos de postagens
}
