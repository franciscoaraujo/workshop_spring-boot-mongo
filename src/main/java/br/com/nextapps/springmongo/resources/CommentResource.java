package br.com.nextapps.springmongo.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.dto.CommentDTO;
import br.com.nextapps.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/comments")
public class CommentResource {
	
	@Autowired 
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CommentDTO> save(@RequestBody CommentDTO obj) {
		
		Post post =	postService.findById(obj.getAuthor().getId());
		post.getComments().add(obj);
		post = postService.savePost(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
