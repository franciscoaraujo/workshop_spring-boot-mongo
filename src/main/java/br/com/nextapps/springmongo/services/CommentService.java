package br.com.nextapps.springmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nextapps.springmongo.dto.CommentDTO;
import br.com.nextapps.springmongo.repositories.CommentRespository;
import br.com.nextapps.springmongo.services.exception.ObjectNotFoundException;

@Service
public class CommentService {

	@Autowired
	private CommentRespository repo;
	
	@Autowired
	private UserService user;
	
	@Autowired
	private PostService postService;

	public List<CommentDTO> findAll() {
		return repo.findAll();
	}

	public CommentDTO findById(String id) {
		return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public CommentDTO saveComment(CommentDTO comment) {
		
		
		return repo.insert(comment);
	}

}
