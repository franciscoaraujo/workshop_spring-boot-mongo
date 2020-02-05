package br.com.nextapps.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nextapps.springmongo.domain.User;
import br.com.nextapps.springmongo.repositories.UserRepository;
import br.com.nextapps.springmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	
	public List<User>findAll(){
		return repo.findAll();
	}
	
	public User findaById(String id) {
		Optional<User> user =  repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User saveUser(User user) {
		return repo.insert(user);
	}
}
