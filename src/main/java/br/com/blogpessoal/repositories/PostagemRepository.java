package br.com.blogpessoal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blogpessoal.models.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	/**
	 * Metodo utilizado para realizar pesquisa pela coluna titulo da tabela postagem
	 * 
	 * @param titulo
	 * @return List com Postagem
	 * @author Abner Werley Silva
	 * @since 1.0
	 * 
	 */
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

}
