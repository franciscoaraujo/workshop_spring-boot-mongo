package br.com.nextapps.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nextapps.springmongo.dto.CommentDTO;

public interface CommentRespository extends MongoRepository<CommentDTO, String>{
	
}
