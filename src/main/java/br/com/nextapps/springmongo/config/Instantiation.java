package br.com.nextapps.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.domain.User;
import br.com.nextapps.springmongo.dto.AuthorDTO;
import br.com.nextapps.springmongo.dto.CommentDTO;
import br.com.nextapps.springmongo.repositories.PostRepository;
import br.com.nextapps.springmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		/*salvando usuario*/
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
//		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
//		
//		/*Criando posts e relacionando ao usuario*/
//		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abracos!",new AuthorDTO(maria));
//		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei hoje feliz!!", new AuthorDTO(maria));
//		Post post3 = new Post(null, sdf.parse("23/03/2018"), "Good morning !","Happy to day", new AuthorDTO(maria));
//		
//		/*Criando comentarios e relacionando a usuarios*/
//		CommentDTO c1 = new CommentDTO("Boa viagem mano !!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
//		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
//		CommentDTO c3 = new CommentDTO("Tenha um otimo dia !!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
//		
//		/*Adicionando comentarios a post*/
//		post1.getComments().addAll(Arrays.asList(c1, c2));
//		post2.getComments().addAll(Arrays.asList(c3));
//		
//		/*Salvando o post*/
//		postRepository.saveAll(Arrays.asList(post1, post2, post3));//salvando posts
//		
//		/*Salvando usuario com post*/
//		maria.getPosts().addAll(Arrays.asList(post1,post2, post3));
//		userRepository.saveAll(Arrays.asList(maria));
	}

}

/*	1) - Salvar o usuario
 *  2) - Cria o post 
 *  3) - Relaciona o post com o usario 
 *  4) - Relacionao o usuario com o post
 *  
 */