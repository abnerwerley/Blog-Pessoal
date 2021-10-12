package br.com.blogpessoal.modelsDTOs;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Espelho para logar no sistema
 * 
 * @author Abner Werley Silva
 * @since 1.0
 *
 */
public class UsuarioLoginDTO {

	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 5, max = 15) String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
