package br.com.blogpessoal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.blogpessoal.models.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	private @Autowired UsuarioRepository repositorio;

	@BeforeEach
	// dois usuarios: se não existirem no banco de dados, serão cadastrados
	void start() {
		Usuario usuario1 = new Usuario("Bernardo Luiz", "bernardo@email.com", "senhaqualquer");
		if (!repositorio.findByEmail(usuario1.getEmail()).isPresent())
			repositorio.save(usuario1);

		Usuario usuario2 = new Usuario("Luiza Luiz", "luiza@email.com", "senhaQualquer");
		if (!repositorio.findByEmail(usuario2.getEmail()).isPresent())
			repositorio.save(usuario2);
	}

	@Test
	// testa se existe o email cadastrado
	void findByEmailExistentReturnTrueRetornaDoisUsuarios() {
		Optional<Usuario> optionalBernardo = repositorio.findByEmail("bernardo@email.com");

		assertTrue(optionalBernardo.get().getEmail().equals("bernardo@email.com"));
	}

	@Test
	void findAllByNomeContainingIgnoreCaseReturnTwoUsuario() {
		List<Usuario> lista = repositorio.findAllByNomeContainingIgnoreCase("Luiz");

		assertEquals(2, lista.size());

	}

	@AfterAll
	void fim() {
		System.out.println("Testes finalizados");
	}
}
