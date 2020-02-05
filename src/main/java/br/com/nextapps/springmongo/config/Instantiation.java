package br.com.nextapps.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.domain.User;
import br.com.nextapps.springmongo.repositories.PostRepository;
import br.com.nextapps.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		UserRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		UserRepository.saveAll(Arrays.asList(maria, alex, bob));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abracos!",
				maria);
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei hoje feliz!!", maria);

		postRepository.saveAll(Arrays.asList(post1, post2));

	}

}
