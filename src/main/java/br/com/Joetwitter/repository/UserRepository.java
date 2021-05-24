package br.com.Joetwitter.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.Joetwitter.model.User;


public interface UserRepository extends CrudRepository<User, String>{

}
