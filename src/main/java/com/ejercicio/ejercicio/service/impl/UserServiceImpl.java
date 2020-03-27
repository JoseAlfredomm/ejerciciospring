package com.ejercicio.ejercicio.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ejercicio.ejercicio.entity.User;
import com.ejercicio.ejercicio.repository.UserRepository;
import com.ejercicio.ejercicio.service.UserService;

import antlr.collections.List;
import ch.qos.logback.core.encoder.Encoder;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(userName).get();
		
		if(user == null) {
			throw new UsernameNotFoundException("Username: " + userName + "no found");
		}
		
		return user;
	}

	@Override
	public Optional<User> getUserByNameAndPassword(String userName, String password) {
		Optional<User> optionalUser = userRepository.findByUserNameAndPassword(userName, password);
		return optionalUser.isPresent() ? optionalUser :  getOptional();
	}
	
	public Optional<User> getOptional(){
		
		return null;
	}

}
