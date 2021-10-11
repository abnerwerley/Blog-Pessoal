package br.com.blogpessoal.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
// @Table(name = "tb_postagem") // dentro do banco de dados, esses dados virarão uma tabela
public class Postagem {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id_postagem;

	private @NotBlank @Size(min = 5, max = 100) String titulo;

	private @NotBlank @Size(min = 10, max = 500) String texto;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataPostagem = LocalDate.now();
	// vai pegar a data exata da postagem
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@JsonIgnoreProperties({ "minhasPostagens" })
	private Usuario criador;

	@ManyToOne
	@JoinColumn(name = "id_theme")
	@JsonIgnoreProperties({ "postagens" })
	private Theme temaRelacionado;

	public long getId_postagem() {
		return id_postagem;
	}

	public void setId_postagem(long id_postagem) {
		this.id_postagem = id_postagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDate getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(LocalDate dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Theme getTemaRelacionado() {
		return temaRelacionado;
	}

	public void setTemaRelacionado(Theme temaRelacionado) {
		this.temaRelacionado = temaRelacionado;
	}

	

	/*
	 * Existe o banco de dados isolado, e o código isolado, em sistemas diferentes.
	 * Para eles se comunicarem, é necessário uma API. Cada linguagem tem sua API
	 * específica, uma das API's pro java é o SPRING, o SPRING TOOL SUITE 4 já tem a
	 * API no próprio compilador. No Spring, existem as dependências, uma delas é a
	 * Spring Data JPA, que finalmente, linka os dados do banco de dados (em
	 * especial o mySQL) para poder ser usado no programa.
	 */
}
