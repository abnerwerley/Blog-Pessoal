package br.com.blogpessoal.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

	public Usuario usuario;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	public void start() {
		usuario = new Usuario("Aninha da Silva", "aninha@gmail.com", "senha");
	}

	@Test
	void validaUsuarioCorretoRetornaTrue() {
		Set<ConstraintViolation<Usuario>> objetoViolado = validator.validate(usuario);

		assertTrue(objetoViolado.isEmpty());
	}

	@Test
	void validaUsuarioIncorretoRetornaFalse() {
		Usuario usuarioErrado = new Usuario(null, null, "senha1");
		Set<ConstraintViolation<Usuario>> objetoViolacao = validator.validate(usuarioErrado);

		assertFalse(objetoViolacao.isEmpty());
	}
}
