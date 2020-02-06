package br.com.nextapps.springmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.repositories.PostRepository;
import br.com.nextapps.springmongo.services.exception.DataIntegrityException;
import br.com.nextapps.springmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public List<Post> findAllPosts(){
		return repo.findAll();
	}
	
	public Post savePost(Post obj) {
		return repo.insert(obj);
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 20 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	public void deletePost(String id) {
		findById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há Usuario relacionados");
		}
	}
	
}
