package br.com.generation.blog.pessoal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.blog.pessoal.models.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

	/**
	 * Método utilizado para pesquisa pela coluna theme da tb_theme
	 * 
	 * @param theme
	 * 
	 * @return List com theme
	 * 
	 * @author Abner Werley Silva
	 * 
	 * @since 1.0
	 * 
	 */
	public List<Theme> findByThemeContainingIgnoreCase(String theme);

}
