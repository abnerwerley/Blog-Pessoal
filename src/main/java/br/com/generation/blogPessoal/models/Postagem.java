package br.com.generation.blogPessoal.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
// @Table(name = "tb_postagem") // dentro do banco de dados, esses dados virarão uma tabela
public class Postagem {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id_postagem;

	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;

	@NotNull
	@Size(min = 10, max = 500)
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis()); // assim que passar um objeto por essa classe,
																		// vai pegar a data exata da poostagem

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
