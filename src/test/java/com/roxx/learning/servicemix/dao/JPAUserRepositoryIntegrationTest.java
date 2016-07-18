package com.roxx.learning.servicemix.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.roxx.learning.servicemix.domain.Adresse;
import com.roxx.learning.servicemix.domain.User;

/**
 * Integration test using h2.<br>
 * 
 * @author julien
 *
 */
public class JPAUserRepositoryIntegrationTest {

	private static EntityManagerFactory emf;
	private static EntityManager entityManager;

	private JPAUserRepository repository;

	@BeforeClass
	public static void init() {
		emf = Persistence.createEntityManagerFactory("myPU");
		entityManager = emf.createEntityManager();
	}

	@AfterClass
	public static void closeTestFixture() {
		entityManager.close();
		emf.close();
	}

	@Before
	public void setup() {
		repository = new JPAUserRepository(entityManager);
		entityManager.getTransaction().begin();
	}

	@After
	public void teardown() {
		entityManager.getTransaction().rollback();
	}

	@Test
	public void shoud_found_user_julien() {
		// given
		String username = "julien";
		insertUser(username, "my@email.com");
		// when
		List<User> users = repository.findByName(username);

		// then
		User expectedUser = expectedUser();
		assertThat(users).hasSize(1);
		assertThat(users).contains(expectedUser);

	}

	@Test
	public void shoud_not_found_user_test() {
		// given
		String username = "test";
		insertUser("julien", "my@email.com");
		insertUser("julie", "julie@test.com");

		// when
		List<User> users = repository.findByName(username);

		// then
		assertThat(users).hasSize(0);
	}

	@Test
	public void shoud_found_3_users() {
		// given
		String username = "jul";
		insertUser("julien", "my@email.com");
		insertUser("julie", "julie@test.com");
		insertUser("rajulian", "julie@test.com");

		// when
		List<User> users = repository.findByName(username);

		// then
		assertThat(users).hasSize(3);
	}

	private User expectedUser() {
		User user = new User();
		user.setId(1l);
		user.setName("julien");
		user.setEmail("my@email.com");
		return user;
	}

	private User insertUser(String name, String email) {
		User freshNewUser = new User();
		freshNewUser.setName(name);
		freshNewUser.setEmail(email);
		repository.createUser(freshNewUser);
		return freshNewUser;
	}

	@Test
	public void should_create_user_julien() throws IOException {
		// given
		User userToCreate = new User();
		userToCreate.setName("julien");
		userToCreate.setEmail("julien@email.com");
		userToCreate.setAvatar(loadAvatarImage());
		Adresse addr = new Adresse();
		addr.setNumeroEtVoie("3, rue du rhone");
		addr.setCodePostal("1200");
		addr.setVille("genève");
		List<Adresse> adresses = new ArrayList<Adresse>();
		adresses.add(addr);
		userToCreate.setAdresses(adresses);

		// when
		User createdUser = repository.createUser(userToCreate);

		// then
		assertThat(createdUser.getId()).isNotNull();
		assertThat(createdUser.getName()).isEqualTo("julien");
		assertThat(createdUser.getEmail()).isEqualTo("julien@email.com");
		assertThat(createdUser.getAvatar()).isNotNull();
		assertThat(createdUser.getAdresses()).hasSize(1);
		assertThat(createdUser.getAdresses().get(0).getVille()).isEqualTo("genève");

	}

	private byte[] loadAvatarImage() throws IOException {
		BufferedImage originalImage = ImageIO.read(new File("src/test/resources/gravatar.png"));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "png", baos);
		baos.flush();
		byte[] imageInByte = baos.toByteArray();
		baos.close();
		return imageInByte;
	}

}
