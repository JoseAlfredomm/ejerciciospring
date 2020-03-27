package com.ejercicio.ejercicio.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ejercicio.ejercicio.entity.User;
@Repository
public interface UserRepository  extends CrudRepository<User, Integer> {
	Optional<User> findByUserNameAndPassword(String userName,String password);
	Optional<User> findByUserName(String userName);
	
}
