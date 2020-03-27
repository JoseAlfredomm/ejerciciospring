package com.ejercicio.ejercicio.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ejercicio.ejercicio.entity.User;

public interface UserService extends UserDetailsService{
	Optional<User> getUserByNameAndPassword(String userName,String password);
}
