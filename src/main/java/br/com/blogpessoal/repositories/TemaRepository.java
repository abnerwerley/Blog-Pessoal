package br.com.blogpessoal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.blogpessoal.models.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	/**
	 * Método utilizado para pesquisa pela coluna theme da tb_theme
	 * 
	 * @param theme
	 * 
	 * @return Lista com temas
	 * 
	 * @author Abner Werley Silva
	 * 
	 * @since 1.0
	 * 
	 */
	public List<Tema> findByTemaContainingIgnoreCase(String tema);

}
