package br.com.nextapps.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nextapps.springmongo.domain.Post;
import br.com.nextapps.springmongo.domain.User;
import br.com.nextapps.springmongo.dto.UserDTO;
import br.com.nextapps.springmongo.services.UserService;
import br.com.nextapps.springmongo.services.exception.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	// @RequestMapping(method = RequestMethod.GET) ou usar o de baixo
	@GetMapping
	public ResponseEntity<List<UserDTO>> findaAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);// no corpo da minha resposta vai ter o list de users
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		UserDTO userDto = new UserDTO(user);
		return ResponseEntity.ok().body(userDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDTO> findById(@RequestBody UserDTO objDTO) {
		User user = UserDTO.fromDTO(objDTO);
		user = service.saveUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deletUser(@PathVariable String id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterarUsuario(@RequestBody UserDTO objDTO, @PathVariable String id)
			throws ObjectNotFoundException {

		User user = UserDTO.fromDTO(objDTO);
		user.setId(id);
		service.update(user);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) throws ObjectNotFoundException {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}

}
