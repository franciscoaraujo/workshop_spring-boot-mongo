package br.com.nextapps.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.nextapps.springmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String> {
	
}
