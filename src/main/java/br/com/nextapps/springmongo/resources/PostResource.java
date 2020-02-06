package br.com.nextapps.springmongo.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.domain.User;
import br.com.nextapps.springmongo.dto.PostDTO;
import br.com.nextapps.springmongo.resources.util.URL;
import br.com.nextapps.springmongo.services.PostService;
import br.com.nextapps.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findAllPosts() {
		List<Post> posts = service.findAllPosts();
		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<PostDTO> save(@RequestBody PostDTO obj) {
		Post post = PostDTO.fromPostDTO(obj);
		post = service.savePost(post);
		//Relacionando o usuario ao post
		User user = userService.findById(obj.getAuthor().getId());
		user.getPosts().addAll(Arrays.asList(post));
		userService.update(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.dataConvertDate(minDate, new Date(0L));
		Date max = URL.dataConvertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PostDTO> delet(@PathVariable String id) {
		service.deletePost(id);
		return ResponseEntity.noContent().build();
	}

}
