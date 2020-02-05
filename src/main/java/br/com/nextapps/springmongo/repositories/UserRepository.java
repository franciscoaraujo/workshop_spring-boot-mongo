package br.com.nextapps.springmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.nextapps.springmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
