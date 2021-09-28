package br.com.generation.blogPessoal.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Theme {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id_theme;

	private @NotBlank String theme;

	private @NotBlank String description;

	public long getId_theme() {
		return id_theme;
	}

	public void setId_theme(long id_theme) {
		this.id_theme = id_theme;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
